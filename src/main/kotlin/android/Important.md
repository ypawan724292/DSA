## How Does ViewModel Survive Configuration Changes?

The key to the ViewModel's persistence lies in its relationship with the ViewModelStore,
which is managed by a ViewModelStoreOwner (e.g., Activity, Fragment).
Here's an in-depth look at how this works:

1. **The Lifecycle of an Activity**
   When an Activity undergoes a configuration change, the Android system destroys the old instance and creates a new
   one.
   To retain certain objects (like the ViewModel) across this recreation, the Android system provides the
   NonConfigurationInstance mechanism.
   The ComponentActivity class (a part of androidx.activity) uses this mechanism to retain the ViewModelStore, which is
   a container for ViewModel instances.
   Upon recreation, the new Activity instance retrieves the same ViewModelStore, allowing it to access existing
   ViewModel instances.


2. **The Role of ViewModelStore :**
   The ViewModelStore is a simple map-like structure that holds ViewModel instances, keyed by their names. Here’s how
   it’s defined:

   ```
   public class ViewModelStore {
        private final HashMap<String, ViewModel> mMap = new HashMap<>();
        
        void put(String key, ViewModel viewModel) {
            mMap.put(key, viewModel);
        }
        
        ViewModel get(String key) {
            return mMap.get(key);
        }
        void clear() {
            for (ViewModel vm : mMap.values()) {
               vm.clear();
            }
            mMap.clear();
        }
    }
   ```


    ```
    public class ComponentActivity extends Activity implements ViewModelStoreOwner {
        @Override
        protected Object onRetainNonConfigurationInstance() {
             return mViewModelStore;
        }
        //During recreation, the retained ViewModelStore is restored:
        @Override
        public Object getLastNonConfigurationInstance() {
            return mViewModelStore; 
        }   
    }
    ```

---

https://www.geeksforgeeks.org/top-50-android-interview-questions-answers-sde-i-to-sde-iii/


## Launch Modes of an Activity
Standard: Default, new instance each time.
SingleTop: Reuses the top instance if it exists.
SingleTask: Reuses an existing instance in the task, clearing above activities.
SingleInstance: Ensures the activity is the only one in its task


## Application start ways

1. Cold Start: App is not running, and the user launches it from scratch.
```
   Application.onCreate()
   Activity.onCreate()
   Activity.onStart()
   Activity.onResume()
```


2. Warm Start: App is running in the background, and the user launches it.
```
   Activity.onStart()
   Activity.onResume()

``` 
   

3. Hot Start: App is running in the foreground, and the user launches it.
```
    Activity.onResume()
```

### 7. What is Google Android SDK? Which are the tools placed in Android SDK?
The Google Android SDK is a toolset used by developers to write applications on Android-enabled devices.

The tools placed in Android SDK are given below:

Android Emulator - Android Emulator is a software application that simulates Android devices on your computer so that you can test the application on a variety of devices and Android API levels without having each physical device.
DDMS(Dalvik Debug Monitoring Services) - It is a debugging tool from the Android software development kit (SDK) which provides services like message formation, call spoofing, capturing screenshots, etc.
ADB(Android Debug Bridge) - It is a command-line tool used to allow and control communication with the emulator instance.
AAPT(Android Asset Packaging Tool) - It is a build tool that gives the ability to developers to view, create, and update ZIP-compatible archives (zip, jar, and apk).