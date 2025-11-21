# UCS Monywa Android App - AI Agent Instructions

## Project Overview
University of Computer Studies (UCS) Monywa Android app built with **Java 17** for managing timetables, notice boards, exam results, and campus activities. Uses Firebase for storage/database and a Node.js backend API for timetable data.

**Package**: `com.admin.ucsmonywa` | **Min SDK**: 21 | **Target SDK**: 34 | **Build System**: Gradle 8.4 + AGP 8.2.2

## Architecture & Data Flow

### Centralized Constants Pattern
**ALL constants are centralized** in `constants/AppConstants.java`:
- API configuration (`Api.BASE_URL`, timeouts)
- Firebase paths (`Firebase.NOTICEBOARD_PATH`, `Firebase.STORAGE_IMAGES_PATH`)
- SharedPreferences keys (`Preferences.KEY_YEAR`, `Preferences.KEY_SECTION`)
- Intent extra keys (`IntentKeys.SUBJECT`, `IntentKeys.TEACHER`, etc.)
- Academic data (year/section arrays)
- UI messages

**Critical**: Never hardcode values that exist in `AppConstants`. Use the constants.

### Year-Specific Model Structure (Code Duplication by Design)
The app uses **5 identical model packages** with NO inheritance:
- `firstyear_model/`, `secondyear_model/`, `thirdyear_model/`, `fourthyear_model/`, `fifthyear_model/`
- Each contains: `*YearData`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `SheduleItem`
- `*YearData` wraps weekday objects with `@SerializedName` annotations for Gson
- Each weekday class contains `List<SheduleItem>` (subject, teacher, duration)
- Separate adapters (`FirstYearAdapter`, `SecondYearAdapter`, etc.) per year

**Why this matters**: When adding features to models/adapters, you must replicate across all 5 year packages. The structure is duplicated intentionally, not inherited.

### API Integration Pattern (Three-Layer Architecture)
1. **Constants Layer**: `constants/AppConstants.Api.BASE_URL` = `http://192.168.100.72:3000/`
2. **Client Layer**: `api/ApiClient.java` - Singleton Retrofit instance with:
   - OkHttpClient with 30s timeouts (configurable via `AppConstants.Api.*_TIMEOUT`)
   - Gson converter factory
   - Lazy initialization pattern
3. **Service Layer**: `api/ApiInterface.java` - 25 endpoint methods (5 years × 5 sections)
4. **Access Layer**: `utils/ApiUtils.getApi()` - Single entry point for all API calls

**Endpoint pattern**: `GET /{year}/{section}` → Returns `*YearData` object
- First Year: Sections A, B, C, D, E
- Years 2-5: Sections A, B, C, D, CT (Computer Technology)
- Example: `/firstyear/A`, `/secondyear/CT`, `/fifthyear/D`

### Firebase Integration
- **Firebase Realtime Database**: Notice board data under `/noticeboard` node
- **Firebase Storage**: Notice board images uploaded to `images/` path with timestamp naming
- **Firebase Auth**: Configured but authentication flow not implemented
- Model: `ImageUploadInfo` (imageTitle, imageDetail, image URL, date)

## Navigation & Fragment Management

### Main Navigation Flow
`MainActivity` uses `NavigationDrawer` with 5 main sections:
1. **Timetable** (default) → `TimetableFragment` → selection screen → `TimetableActivity`
2. **Notice Board** → `NoticeBoardFragment` (Firebase RecyclerView)
3. **Exam Result** → `ResultFragment`
4. **Activities** → `ActivityFragment` (campus events)
5. **About** → `AboutFragment`

### SharedPreferences Pattern (New Singleton Manager)
Year/section selections persist via **centralized PreferenceManager**:
- Singleton: `utils/PreferenceManager.getInstance(context)`
- File: `AppConstants.Preferences.PREF_FILE_NAME` = `"com.internship.ucsmonywa"`
- Methods: `saveYear()`, `getYear()`, `saveSection()`, `getSection()`, `clear()`
- **Legacy code**: Some fragments still use direct SharedPreferences access (being migrated)
- Stored in `TimetableFragment.onPause()`, retrieved in weekday fragments
- **Critical**: Always read preferences in `onCreateView()` before API calls

**Example**:
```java
PreferenceManager prefManager = PreferenceManager.getInstance(getContext());
String year = prefManager.getYear();
String section = prefManager.getSection();
```

### Weekday Fragment Pattern
Each weekday (Monday-Friday) has identical fragment structure:
- Single layout: `recycler_layout.xml` (reused)
- Load year/section from SharedPreferences
- Nested if-else to determine API call (5 years × 5 sections = 25 combinations per day)
- Example: `MondayFragment.loadDataFirstYear()` → checks section → calls appropriate endpoint

## Key Development Patterns

### Date/Time Formatting
Use `Utils.java` helpers:
- `DateToTimeFormat()`: Converts ISO 8601 to relative time (PrettyTime library)
- `DateFormat()`: Converts to "E, d MMM yyyy" format
- `getRandomDrawableColor()`: Returns vibrant color from predefined palette for UI variety

### RecyclerView Adapter Pattern
All timetable adapters follow identical structure:
```java
public class *YearAdapter extends RecyclerView.Adapter<*YearViewHolder> {
    List<SheduleItem> *Year;
    // Bind: subject, duration, teacher
    // onClick: Launch UpdateTimetable with Intent extras
}
```

### Activity Lifecycle for Updates
- `NoticeBoardActivity`: Upload image → Firebase Storage → get URL → save to Realtime DB
- `UpdateTimetable`: Receives subject/teacher/duration via Intent extras for editing
- No delete/update API endpoints implemented (only GET operations)

## Build & Development

### Gradle Configuration
- **Gradle**: 8.4, **Android Gradle Plugin**: 8.2.2
- **Java**: 17 (OpenJDK 17.0.16+)
- **compileSdk**: 34, **targetSdk**: 34, **minSdk**: 21
- **Namespace**: `com.admin.ucsmonywa` (defined in build.gradle)
- **AndroidX**: Enabled with parallel builds and caching
- Uses modern plugins DSL instead of legacy `apply plugin`

### Key Dependencies
- **Retrofit 2.9.0** + Gson converter for REST API
- **Room 2.6.1** with annotation processor (declared but not actively used)
- **Firebase BOM 32.7.0**: Auth, Database, Storage (version-managed via Bill of Materials)
- **Glide 4.16.0**: Image loading with annotation processor
- **PrettyTime 5.0.7.Final**: Relative date formatting
- **Material Components 1.11.0**: UI components
- **AndroidX AppCompat 1.6.1**: Backward compatibility

### Build Commands
```bash
gradlew assembleDebug      # Build debug APK
gradlew installDebug       # Install to device
gradlew clean              # Clean build artifacts
```

## Common Tasks

### Adding a New Year Model
1. Create package `*year_model/` with classes: `*YearData`, `Monday`-`Friday`, `SheduleItem`
2. Add API methods in `ApiInterface.java` for all sections
3. Create `*YearAdapter` extending RecyclerView.Adapter
4. Update all 5 weekday fragments with new load method
5. Add spinner option in `TimetableFragment`

### Adding a New Feature to Notice Board
1. Update `ImageUploadInfo` model in `model/`
2. Modify upload flow in `NoticeBoardActivity.add()`
3. Update `NoticeBoardAdapter` to display new fields
4. Ensure Firebase database rules allow the fields

### Changing API Base URL
Update in **one location only**:
- `constants/AppConstants.Api.BASE_URL` constant

**Note**: Old code had duplicated URL in `activity/UpdateTimetable.java` - this has been centralized to `AppConstants`.

## Important Constraints

- **No backend write operations**: API is read-only, only Firebase supports writes
- **Cleartext traffic enabled**: `usesCleartextTraffic="true"` in manifest (for local dev)
- **Error handling**: API failures show Toast messages; all response bodies checked for null
- **Hardcoded color scheme**: 8 vibrant colors in `Utils.vibrantLightColorList`
- **No user authentication**: Firebase Auth configured but login/logout not implemented
- **Android 12+ requirement**: All activities must have explicit `android:exported` attribute

## Error Handling Patterns

### Null Safety Best Practices
- **Always check** `response.body() != null` before accessing API response data
- **Initialize collections** before use (e.g., `List<ImageUploadInfo> list = new ArrayList<>()`)
- **Validate Intent extras** with null checks before using `getStringExtra()` or `getExtras()`
- **Clear lists** before repopulating from Firebase to prevent duplicates
- **Check ProgressDialog** state before dismissing: `if (progressDialog != null && progressDialog.isShowing())`

### API Response Pattern
```java
if (response.isSuccessful() && response.body() != null && response.body().getMonday() != null) {
    // Process data
} else {
    Toast.makeText(getContext(), "No timetable data available", Toast.LENGTH_SHORT).show();
}
```

### Intent Data Pattern
```java
Intent intent = getIntent();
if (intent != null) {
    mTitle = intent.getStringExtra("title");
}
if (mTitle == null) mTitle = "";
```

## Recent Updates (November 2025)

The project has been **modernized for Java 17 compatibility**:
- Upgraded to Gradle 8.4 and Android Gradle Plugin 8.2.2
- Updated to compile/target SDK 34 (Android 14)
- Migrated from deprecated jcenter to mavenCentral
- Replaced legacy `apply plugin` with modern plugins DSL
- Moved package declaration from AndroidManifest to namespace in build.gradle
- Updated all dependencies to latest stable versions (Firebase BoM 32.7.0, Retrofit 2.9.0, etc.)
- Added explicit `android:exported` attributes for Android 12+ compliance
- Enabled parallel builds and Gradle caching for faster builds
