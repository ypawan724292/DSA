**Retrofit: Working & Interview Questions with Answers**

---

## **1. How Retrofit Works?**

Retrofit is a **type-safe HTTP client** for Android that simplifies API calls by converting HTTP responses into Kotlin/Java objects. It internally uses **OkHttp** for networking and provides support for **converters, call adapters, and interceptors**.

### **Step-by-Step Working of Retrofit**

#### **1. API Interface (Define Endpoints)**

You create an interface with Retrofit annotations (`@GET`, `@POST`, etc.).

```kotlin
interface ApiService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int): Response<User>
}
```

#### **2. Create Retrofit Instance**

You configure **Retrofit.Builder** with a base URL and a converter factory.

```kotlin
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create()) // Converts JSON
    .build()

val apiService = retrofit.create(ApiService::class.java)
```

#### **3. Make an API Call**

You execute the API call **synchronously** or **asynchronously**.

##### **Coroutine (Modern Approach)**

```kotlin
val response = apiService.getUser(1)
if (response.isSuccessful) {
    val user = response.body()
}
```

##### **Callback (Traditional)**

```kotlin
apiService.getUser(1).enqueue(object : Callback<User> {
    override fun onResponse(call: Call<User>, response: Response<User>) {
        if (response.isSuccessful) {
            val user = response.body()
        }
    }
    override fun onFailure(call: Call<User>, t: Throwable) {
        println("Error: ${t.message}")
    }
})
```

---

## **2. Retrofit Interview Questions & Answers**

### **1. What is Retrofit in Android?**

**Answer:** Retrofit is a **type-safe HTTP client** for Android that simplifies API calls by converting JSON responses into Kotlin/Java objects. It internally uses **OkHttp** for networking.

### **2. How does Retrofit differ from Volley & OkHttp?**

| Feature                     | Retrofit | Volley | OkHttp |
| --------------------------- | -------- | ------ | ------ |
| **Type-safe API Calls**     | ✅ Yes    | ❌ No   | ❌ No   |
| **Automatic Serialization** | ✅ Yes    | ❌ No   | ❌ No   |
| **Uses OkHttp Internally**  | ✅ Yes    | ❌ No   | ✅ Yes  |
| **Built-in Caching**        | ❌ No     | ✅ Yes  | ✅ Yes  |
| **Image Loading Support**   | ❌ No     | ✅ Yes  | ❌ No   |

### **3. What are the key components of Retrofit?**

**Answer:**

1. **Service Interface**: Defines API endpoints (`@GET`, `@POST`).
2. **Retrofit Builder**: Configures base URL & converter.
3. **Converters**: Convert API responses (`Gson`, `Moshi`, `Scalars`).
4. **Call Adapter**: Handles response (`Call<T>`, Coroutines, RxJava).
5. **OkHttp Client**: Manages HTTP requests with interceptors.

### **4. How to handle API errors in Retrofit?**

**Answer:** Use `Response.isSuccessful` to check for errors.

```kotlin
val response = apiService.getUser(1)
if (!response.isSuccessful) {
    val errorBody = response.errorBody()?.string()
    println("Error: $errorBody")
}
```

For **global error handling**, use **OkHttp Interceptors**:

```kotlin
val interceptor = Interceptor { chain ->
    val response = chain.proceed(chain.request())
    if (!response.isSuccessful) {
        throw IOException("API Error: ${response.code}")
    }
    response
}
```

### **5. What are Retrofit Converters?**

| Converter             | Library                            |
| --------------------- | ---------------------------------- |
| Gson (Default)        | `GsonConverterFactory.create()`    |
| Moshi                 | `MoshiConverterFactory.create()`   |
| Scalars (String, Int) | `ScalarsConverterFactory.create()` |
| Protobuf              | `ProtoConverterFactory.create()`   |

### **6. What are Retrofit Call Adapters?**

| Adapter    | Return Type                  |
| ---------- | ---------------------------- |
| Default    | `Call<T>`                    |
| Coroutines | `suspend fun`                |
| RxJava     | `Single<T>`, `Observable<T>` |

### **7. How does Retrofit use OkHttp?**

**Answer:** Retrofit internally uses **OkHttp** for:

- Making network requests
- Handling caching & interceptors
- Executing API calls asynchronously

### **8. How to cancel an API request in Retrofit?**

**Answer:** Use `Call.cancel()` to stop an ongoing request.

```kotlin
val call = apiService.getUser(1)
call.cancel() // Cancels the API request
```

For **Coroutines**, use **Job cancellation**:

```kotlin
val job = CoroutineScope(Dispatchers.IO).launch {
    val response = apiService.getUser(1)
}
job.cancel() // Cancels coroutine
```

### **9. How to use Retrofit with Dependency Injection (Hilt)?**

**Answer:** Define `Retrofit` as a Singleton using Hilt:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
```

### **10. Difference between enqueue() and execute()?**

| Method        | Behavior                                 |
| ------------- | ---------------------------------------- |
| **enqueue()** | Asynchronous (Runs on background thread) |
| **execute()** | Synchronous (Blocks the main thread)     |

Example:

```kotlin
// Asynchronous
apiService.getUser(1).enqueue(object : Callback<User> {...})

// Synchronous
val response = apiService.getUser(1).execute()
```

---

## Gson vs Moshi: A Detailed Comparison

Both **Gson** and **Moshi** are JSON serialization/deserialization libraries used in Android development. While Gson has been around for longer, Moshi is designed as a modern, more optimized alternative, especially for Kotlin.

---

## 1. Key Differences Between Gson and Moshi

| Feature            | **Gson**  | **Moshi**  |
|--------------------|----------|------------|
| **Performance**    | ❌ Slower (Reflection-heavy) | ✅ Faster (Optimized reflection & code generation) |
| **Null Safety**    | ❌ Allows null values implicitly | ✅ Strictly enforces non-null fields |
| **Kotlin Support** | ❌ Requires extra configuration (e.g., `@SerializedName`) | ✅ Native support for Kotlin (works well with `val` and default values) |
| **Annotations**    | `@SerializedName("name")` | `@Json(name = "name")` |
| **Immutable Data** | ❌ Requires `var` fields or custom deserialization | ✅ Supports `val` fields directly |
| **Default Values** | ❌ Requires custom deserialization | ✅ Uses Kotlin's default parameter values |
| **Adapter Support** | ✅ Yes (Custom Type Adapters) | ✅ Yes (Built-in and Custom Adapters) |
| **Code Generation** | ❌ No | ✅ Yes (with Moshi Kotlin Codegen) |

---

## 2. Gson Example in Retrofit

### Step 1: Add Dependencies
```gradle
dependencies {
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.google.code.gson:gson:2.10.1'
}
```

### Step 2: Define Data Model
```kotlin
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val userId: Int,
    @SerializedName("name") val userName: String,
    @SerializedName("email") val userEmail: String
)
```

### Step 3: Create Retrofit Instance
```kotlin
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```

---

## 3. Moshi Example in Retrofit

### Step 1: Add Dependencies
```gradle
dependencies {
    implementation 'com.squareup.retrofit2:converter-moshi:2.9.0'
    implementation 'com.squareup.moshi:moshi-kotlin:1.14.0'
}
```

### Step 2: Define Data Model
```kotlin
import com.squareup.moshi.Json

data class User(
    @Json(name = "id") val userId: Int,
    @Json(name = "name") val userName: String,
    @Json(name = "email") val userEmail: String
)
```

### Step 3: Create Retrofit Instance
```kotlin
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
```

---

## 4. Which One Should You Use?

### Use **Moshi** if:
- ✅ You're working with **Kotlin** (Better null safety & `val` support).
- ✅ You need **better performance** (optimized for speed).
- ✅ You want **default values** without custom deserialization.

### Use **Gson** if:
- ✅ You're working on a **legacy project with Java**.
- ✅ You already have Gson implemented and don’t want to migrate.
- ✅ You need **broad compatibility** across various libraries.

---

## 5. Final Recommendation
For new Android projects in **Kotlin**, **Moshi is the preferred choice** due to:
- ✅ Better Kotlin support
- ✅ Faster performance
- ✅ Safer JSON parsing

For Further refer this link [Interview Questions](https://medium.com/@sujathamudadla1213/android-retrofit-interview-questions-eefdef6dc09c)
---

