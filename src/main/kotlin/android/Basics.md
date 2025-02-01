1. **What is the Android Application Architecture?**
   Android application architecture has the following components:

Content Providers − It will share the data between applications.
Services − It will perform background functionalities
Intent − It will perform the inter connection between activities and the data passing mechanism.
Resource Externalization − strings and graphics.
Notification − light, sound, icon, notification, dialog box and toast.

2. What is the Application class?
   The Application class in Android is the base class within an Android app that contains all other components such as
   activities and services. The Application class, or any subclass of the Application class, is instantiated before any
   other class when the process for your application/package is created.

3. What is Context?
   A Context is a handle to the system; it provides services like resolving resources, obtaining access to databases and
   preferences, and so on. An Android app has activities. Context is like a handle to the environment your application
   is currently running in.

Application Context: This context is tied to the lifecycle of an application. The application context can be used when
you need a context whose lifecycle is separate from the current context or when you are passing a context beyond the
scope of an activity.
Activity Context: This context is available in an activity. This context is tied to the lifecycle of an activity. The
activity context should be used when you are passing the context in the scope of an activity or you need the context
whose lifecycle is attached to the current context.

4. What are the four components of an android application?
   An Android application is built around four main components, each serving a distinct purpose in how the application
   operates and interacts with the system and other applications. These four components are:

Activities
Services
Broadcast Receivers
Content Providers

5. Describe services
   A Service is a component that performs long-running operations in the background. Services do not provide a user
   interface; instead, they run in the background to handle tasks like data processing, file downloading, or playing
   music, even if the app isn’t in the foreground. These are the three different types of services:

Foreground Service: Runs actively and is visible to the user through a persistent notification in the notification bar.
It is commonly used for tasks that the user is explicitly aware of, such as music players or navigation apps. Foreground
services have a higher priority than other services.

Background Service: Runs in the background without user interaction. Since Android 8.0 (Oreo), background services are
restricted due to battery and performance optimizations. Background tasks are generally handled by WorkManager or
JobScheduler for greater efficiency.

Bound Service: Allows components (like activities and fragments) to bind to the service, enabling communication. For
example, a bound service could handle requests and return results to a client, such as fetching data or updating an
activity with real-time information.

6. Explain the Service lifecycle.
   Services have a distinct lifecycle that differs from activities. Understanding these methods is key to managing
   resources and preventing memory leaks:

onCreate(): Called when the service is created for the first time. This is where you initialize resources such as
setting up connections or data processing.
onStartCommand(): Called each time a client starts the service using startService(). This method is where the service
performs its main task. You can decide to keep the service running indefinitely or to stop it once the task is
completed.
onBind(): Called when another component wants to bind with the service by calling bindService(). A bound service uses
this to return an IBinder instance for communication.
onUnbind(): Called when all clients unbind from the service.
onDestroy(): Called when the service is being terminated. This is where resources are released, and any running tasks
are cleaned up.

7. Difference between Service & Intent Service
   A Service runs on the main (UI) thread of the application. When an IntentService is started, it creates a worker
   thread to handle all intent requests sequentially.
   A Service can handle multiple tasks simultaneously but requires additional threading management. An IntentService
   processes intents sequentially in a single background worker thread. When an intent is sent to an IntentService, it
   queues the work and processes each request in order, one at a time, until all tasks are complete.
   Service is ideal for long-running tasks that require interaction or need to keep running in the background even if
   the user has left the app. IntentService is ideal for short, discrete background tasks that don’t require prolonged
   activity or user interaction, such as: downloading a file, uploading data to a server, processing or syncing data in
   the background.

8. What is Android Bound Service?
   A Bound Service in Android is a type of service that allows other components, like activities or fragments, to bind
   to it and interact with it directly. Unlike a started service that runs independently, a bound service provides a
   client-server interface and remains active only as long as there are clients bound to it. Bound services are ideal
   when you need to maintain an interactive, continuous connection between the service and clients, such as: Real-Time
   Data Syncing, Media Playback, Location Updates etc.

9. Explain the difference between IntentService and JobIntentService.
   IntentService is a specialized Service that handles each incoming intent on a worker thread, automatically stopping
   itself when all tasks are completed. It’s useful for lightweight, immediate tasks that need to run in the background.
   However, IntentService runs only as long as the app is in the foreground or has a persistent process, as it doesn’t
   survive app process termination.
   JobIntentService, introduced as part of Android’s support library, was designed to work better with these background
   restrictions. It combines the features of IntentService with JobScheduler, enabling tasks to be completed even if the
   app is no longer in the foreground. When the device is running Android 8.0 or later, JobIntentService uses the
   JobScheduler API, which allows tasks to persist across app restarts or even device reboots. It’s ideal for tasks that
   are not immediate but need guaranteed execution at some point, like uploading logs or syncing data.
10. Describe activities
    An Activity represents a single screen with a user interface. It acts as the entry point for interacting with the
    app and serves as a container for views. Each activity is typically associated with a layout file that defines the
    UI components.

11. Lifecycle of an Activity
    OnCreate(): This is when the view is first created. This is normally where we create views, get data from bundles
    etc.
    OnStart(): Called when the activity is becoming visible to the user. Followed by onResume() if the activity comes to
    the foreground, or onStop() if it becomes hidden.
    OnResume(): Called when the activity will start interacting with the user. At this point your activity is at the top
    of the activity stack, with user input going to it.
    OnPause(): Called as part of the activity lifecycle when an activity is going into the background, but has not (yet)
    been killed.
    OnStop(): Called when you are no longer visible to the user.
    OnDestroy(): Called when the activity is finishing
    OnRestart(): Called after your activity has been stopped, prior to it being started again.
12. What’s the difference between onCreate() and onStart()?
    The onCreate() method is called once during the Activity lifecycle, either when the application starts, or when the
    Activity has been destroyed and then recreated, for example during a configuration change.
    The onStart() method is called whenever the Activity becomes visible to the user, typically after onCreate() or
    onRestart().
13. Scenario in which only onDestroy is called for an activity without onPause() and onStop()?
    If finish() is called in the OnCreate() method of an activity, the system will invoke onDestroy() method directly.

14. Why would you set the setContentView() in onCreate() of Activity class?
    As onCreate() of an Activity is called only once, this is the point where most initialisation should go. It is
    inefficient to set the content in onResume() or onStart() (which are called multiple times) as the setContentView()
    is a heavy operation.

15. Difference between onSavedInstanceState() and onRestoreInstanceState() in an activity?
    OnRestoreInstanceState() – When activity is recreated after it was previously destroyed, we can recover the saved
    state from the Bundle that the system passes to the activity. Both the onCreate() and onRestoreInstanceState()
    callback methods receive the same Bundle that contains the instance state information. But because the onCreate()
    method is called whether the system is creating a new instance of your activity or recreating a previous one, you
    must check whether the state Bundle is null before you attempt to read it. If it is null, then the system is
    creating a new instance of the activity, instead of restoring a previous one that was destroyed.
    onSaveInstanceState() – is a method used to store data before pausing the activity.
16. Launch modes in Android?
    Standard: It creates a new instance of an activity in the task from which it was started. Multiple instances of the
    activity can be created and multiple instances can be added to the same or different tasks. Eg: Suppose there is an
    activity stack of A -> B -> C. Now if we launch B again with the launch mode as “standard”, the new stack will be
    A -> B -> C -> B.
    SingleTop: It is the same as the standard, except if there is a previous instance of the activity that exists in the
    top of the stack, then it will not create a new instance but rather send the intent to the existing instance of the
    activity. Eg: Suppose there is an activity stack of A -> B. Now if we launch C with the launch mode as “singleTop”,
    the new stack will be A -> B -> C as usual. Now if there is an activity stack of A -> B -> C. If we launch C again
    with the launch mode as “singleTop”, the new stack will still be A -> B -> C.
    SingleTask: A new task will always be created and a new instance will be pushed to the task as the root one. So if
    the activity is already in the task, the intent will be redirected to onNewIntent() or else a new instance will be
    created. At a time only one instance of activity will exist. Eg: Suppose there is an activity stack of A -> B ->
    C -> D. Now if we launch D with the launch mode as “singleTask”, the new stack will be A -> B -> C -> D as usual.
    Now if there is an activity stack of A -> B -> C -> D. If we launch activity B again with the launch mode as
    “singleTask”, the new activity stack will be A -> B. Activities C and D will be destroyed.
    SingleInstance: Same as single task but the system does not launch any activities in the same task as this activity.
    If new activities are launched, they are done so in a separate task. Eg: Suppose there is an activity stack of A ->
    B -> C -> D. If we launch activity B again with the launch mode as “singleInstance”, the new activity stack will be:
    Task1 — A -> B -> C; Task2 — D.
17. How does the activity respond when the user rotates the screen?
    When the screen is rotated, the current instance of activity is destroyed a new instance of the Activity is created
    in the new orientation. The onRestart() is invoked first when a screen is rotated. The other lifecycle methods get
    invoked in the similar flow as they were when the activity was first created. Following is the sequence of methods
    onPause()
    onStop()
    onDestroy()
    onCreate()
    onStart()
    onResume()

18. Mention two ways to clear the back stack of Activities when a new Activity is called using intent.
    Using FLAG_ACTIVITY_CLEAR_TOP: This flag clears all activities on top of the target activity in the back stack. If
    the target activity is already running in the current task, it will be brought to the front and all other activities
    on top of it will be destroyed.
    Using FLAG_ACTIVITY_CLEAR_TASK and FLAG_ACTIVITY_NEW_TASK: These flags together clear the entire task stack and
    start a new task with the target activity as the root.

19. What’s the difference between FLAG_ACTIVITY_CLEAR_TASK and FLAG_ACTIVITY_CLEAR_TOP?
    FLAG_ACTIVITY_CLEAR_TOP, if set and if an old instance of this Activity exists in the task list then barring that
    all the other activities are removed and that old activity becomes the root of the task list. Else if there’s no
    instance of that activity then a new instance of it is made the root of the task list. Using FLAG_ACTIVITY_NEW_TASK
    in conjunction is a good practice, though not necessary.
    FLAG_ACTIVITY_CLEAR_TASK is used to clear all the activities from the task including any existing instances of the
    class invoked. The Activity launched by intent becomes the new root of the otherwise empty task list. This flag has
    to be used in conjunction with FLAG_ ACTIVITY_NEW_TASK.
20. Difference between Activity & Service
    Activities are basically containers or windows to the user interface. Services is a component that is used to
    perform operations on the background. It does not have an UI.

21. What is the onTrimMemory() method?
    The onTrimMemory() method in Android is a callback that informs an app about its current memory status, allowing it
    to manage resources and release memory when necessary.
    This method is called with different levels of memory urgency, represented by constants like
    TRIM_MEMORY_RUNNING_MODERATE, TRIM_MEMORY_RUNNING_LOW, or TRIM_MEMORY_BACKGROUND, indicating whether the app is in
    the background, has reduced memory, or is at risk of being killed if memory isn’t freed.
22. Explain the purpose of JobScheduler and how it works.
    The JobScheduler API is designed to schedule tasks that need to be run in the background under specific conditions,
    such as network availability, device charging, or idle state. Introduced in Android 5.0 (API level 21), JobScheduler
    allows developers to defer jobs until these optimal conditions are met, enhancing device efficiency and battery life
    by batching background tasks.

23. Describe content providers
    A ContentProvider provides data from one application to another, when requested. It manages access to a structured
    set of data.
    It provides mechanisms for defining data security.
    ContentProvider is the standard interface that connects data in one process with code running in another process.
    When you want to access data in a ContentProvider, you must instead use the ContentResolver object in your
    application’s Context to communicate with the provider as a client.
    The provider object receives data requests from clients, performs the requested action, and returns the results.
24. Access data using Content Provider:
    Start by making sure your Android application has the necessary read access permissions. Then, get access to the
    ContentResolver object by calling getContentResolver() on the Context object, and retrieving the data by
    constructing a query using ContentResolver.query(). This method returns a Cursor, so you can retrieve data from each
    column using Cursor methods.

25. What are BroadcastReceivers?
    Broadcast Receivers allow your app to respond to system-wide events, like network changes or low battery. They can
    be registered in code or manifest and are useful for tasks like reacting to connectivity changes in a messaging app.

26. What is an intent?
    Intents are messages that can be used to pass information to the various components of android. For instance, launch
    an activity, open a webview etc. Two types of intents-

Explicit: Explicit intent is when you call an application activity from another activity of the same application.
Implicit: Implicit intent is when you call system default intent like send email, send SMS, dial number.

27. What is a Sticky Intent?
    A Sticky Intent is an intent that remains in the system after it has been broadcast. When a sticky broadcast is
    sent, it “sticks” around in the system so that future receivers can access the data even if they register after the
    broadcast was originally sent. This can be useful when you want to broadcast an intent that other components might
    need to access at a later time, rather than at the time of broadcast.

Note: Sticky broadcasts were widely used in earlier versions of Android but are discouraged in modern Android
development. The Android team now recommends alternatives like ViewModel and LiveData to achieve similar results more
securely and efficiently.

28. What is a Pending Intent?
    A PendingIntent in Android is a special type of intent that grants another app or system component the permission to
    execute a predefined action on behalf of your application at a later time. It’s essentially a wrapped intent that
    can be handed off to another application or the Android system to execute later, often under specific conditions.
    Example Use Cases:

Notifications: When you create a notification, you usually want to trigger an action when the user clicks it (like
opening an activity). A PendingIntent lets you specify this action in advance.
Alarms with AlarmManager: If you want to schedule an action to be executed at a specific time, you use PendingIntent
with AlarmManager to define the action.
Location Updates: PendingIntent is used with location services to trigger a specific action when a location condition is
met, like entering or exiting a geographical area.

29. What is an Action?
    An Action represents a specific operation or behavior that an Intent can perform. It’s essentially a string constant
    that defines what type of operation should be carried out, and it’s used by both the system and applications to
    specify and handle particular actions. Actions are fundamental in defining the nature of intents, allowing Android
    components (like activities, services, and broadcast receivers) to respond to specific events or tasks.

30. What are intent Filters?
    Intent Filters in Android define the types of intents that a component (such as an Activity, Service, or
    BroadcastReceiver) can respond to. They are specified in the AndroidManifest.xml file or programmatically and allow
    the Android system to determine which components are capable of handling certain intents. Intent filters make it
    possible for apps to interact with each other and respond to system-wide events.

```xml

<activity android:name=".ExampleActivity">
    <intent-filter>
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <data android:scheme="http" android:host="www.example.com"/>
    </intent-filter>
</activity>
```

31. What is a Fragment, and how does it differ from an Activity?
    Fragment is a UI entity attached to Activity. Fragments can be reused by attaching in different activities. Activity
    can have multiple fragments attached to it. Fragment must be attached to an activity and its lifecycle will depend
    on its host activity.

32. Describe fragment lifecycle
    onAttach() : The fragment instance is associated with an activity instance.The fragment and the activity is not
    fully initialized. Typically you get in this method a reference to the activity which uses the fragment for further
    initialization work.
    onCreate() : The system calls this method when creating the fragment. You should initialize essential components of
    the fragment that you want to retain when the fragment is paused or stopped, then resumed.
    onCreateView() : The system calls this callback when it’s time for the fragment to draw its user interface for the
    first time. To draw a UI for your fragment, you must return a View component from this method that is the root of
    your fragment’s layout. You can return null if the fragment does not provide a UI.
    onActivityCreated() : The onActivityCreated() is called after the onCreateView() method when the host activity is
    created. Activity and fragment instance have been created as well as the view hierarchy of the activity. At this
    point, view can be accessed with the findViewById() method. example. In this method you can instantiate objects
    which require a Context object
    onStart() : The onStart() method is called once the fragment gets visible.
    onResume() : Fragment becomes active.
    onPause() : The system calls this method as the first indication that the user is leaving the fragment. This is
    usually where you should commit any changes that should be persisted beyond the current user session.
    onStop() : Fragment going to be stopped by calling onStop()
    onDestroyView() : Fragment view will destroy after call this method
    onDestroy(): called to do final clean up of the fragment’s state but Not guaranteed to be called by the Android
    platform.
33. What is the difference between fragments & activities. Explain the relationship between the two.
    An Activity is an application component that provides a screen, with which users can interact in order to do
    something whereas a Fragment represents a behavior or a portion of user interface in an Activity (with its own
    lifecycle and input events, and which can be added or removed at will).

34. Difference between adding/replacing fragment in backstack?
    replace removes the existing fragment and adds a new fragment. This means when you press back button the fragment
    that got replaced will be created with its onCreateView being invoked.
    add retains the existing fragments and adds a new fragment that means existing fragment will be active and they wont
    be in ‘paused’ state hence when a back button is pressed onCreateView is not called for the existing fragment(the
    fragment which was there before new fragment was added).
    Note: In terms of fragment’s life cycle events onPause(), onResume(), onCreateView() and other life cycle events
    will be invoked in case of replace but they wont be invoked in case of add.
    Calling addToBackStack(null) in a fragment transaction adds the transaction to the back stack

```kotlin

val fragmentTransaction = supportFragmentManager.beginTransaction()
fragmentTransaction.replace(R.id.fragment_container, NewFragment())
fragmentTransaction.addToBackStack(null)
fragmentTransaction.commit()
```

35. What are retained fragments?
    Retained Fragments are fragments that are preserved across configuration changes (like screen rotations). By setting
    a fragment to be retained, you prevent it from being destroyed and recreated when the activity’s configuration
    changes, allowing the fragment to retain its data and state.

To create a retained fragment, you need to call setRetainInstance(true) in the fragment’s onCreate() method. This marks
the fragment as retained, allowing it to survive configuration changes.

36. How do you handle back navigation in a Fragment?
    You can use the OnBackPressedDispatcher to listen for back press events and navigate accordingly.

```kotlin

// MainActivity.kt
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, FirstFragment())
            }
        }
    }
}

// FirstFragment.kt
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        view.findViewById<Button>(R.id.button_to_second_fragment).setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, SecondFragment())
                addToBackStack(null)
            }
        }
        return view
    }
}

// SecondFragment.kt
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle back press when this fragment is active
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Custom behavior on back press
                    parentFragmentManager.popBackStack()
                }
            }
        )
    }
}
```

37. How do you communicate between fragments?
    Using a Shared ViewModel: The most modern and recommended approach, especially within a single activity, is to use a
    shared ViewModel scoped to the Activity. Both fragments observe data within the same ViewModel, allowing seamless
    data sharing. This approach works well with the Android Architecture Components and ensures data consistency even
    through configuration changes.
    Direct Interface Callback: Define an interface in the fragment and let the activity implement it. Fragment A can
    then call methods on this interface, which the activity handles by passing data to Fragment B. This approach is less
    commonly used now with ViewModel but still effective when fragments communicate indirectly through their host
    activity.
    Fragment Result API: Introduced in AndroidX, the Fragment Result API is a direct way to communicate between two
    fragments, often for one-time data exchanges. Fragment A sets a result with a unique key, and Fragment B registers
    to observe this result using that key. It’s simple and scoped to the fragment lifecycle, making it a good choice for
    temporary data passing, like sending a user selection or form data.
    Passing Data through Arguments: for one-way communication (e.g., sending data from Fragment A to Fragment B when
    Fragment B is created), you can pass data through Bundle arguments using Fragment.setArguments(). This method is
    straightforward and primarily used for sending data at the time of fragment creation.
38. What is the difference between onCreateView and onViewCreated in Fragments?
    onCreateView: This method is called when the fragment is creating its view hierarchy, and its primary purpose is to
    inflate the layout for the fragment. You usually override onCreateView to return the root view for your fragment by
    inflating a layout file.
    onViewCreated: This method is called after onCreateView and is meant for additional view setup. It’s triggered after
    the fragment’s view has been created and fully inflated, making it the ideal place to initialize UI components, set
    up listeners, or perform operations that interact with the view.
39. How would you update the UI of an activity from a background service?
    Using BroadcastReceiver: One common method is to use a BroadcastReceiver in the activity to listen for broadcasts
    sent from the service. When the service needs to update the UI, it sends a broadcast with the relevant data, and the
    activity’s receiver handles the UI update. (Sample code)
    You can use a shared ViewModel with LiveData to observe data changes. This approach is especially effective for a
    single-activity application where fragments and services share data using ViewModel. (Sample)
    Messenger allows the service to communicate with a client (activity) using message passing. This approach is
    beneficial if you need two-way communication between the activity and the service. (Sample code)
    You can use LocalBroadcastManager for broadcasting data updates. This is similar to BroadcastReceiver, but
    LocalBroadcastManager confines broadcasts to within the same app, which is more secure and efficient.
40. What is the purpose of the RecyclerView.Adapter?
    The purpose of the RecyclerView.Adapter in Android is to act as a bridge between the data source and the
    RecyclerView UI component.
    It is responsible for creating ViewHolder objects to display each item in the list and binding the data from the
    data source to these ViewHolders. The adapter ensures that only a limited number of views are created and recycled
    efficiently as they scroll off-screen, improving performance by reducing memory usage and avoiding the need to
    create a new view for every item in the list.
    The adapter also manages notifying the RecyclerView about data changes, which allows the list to update dynamically.
    By separating data management from the layout and UI logic, RecyclerView.Adapter promotes a clean, modular approach
    to implementing dynamic lists.
41. What is ConstraintLayout, and why is it useful?
    It allows you to create large and complex layouts with a flat view hierarchy (no nested view groups). It’s similar
    to RelativeLayout in that all views are laid out according to relationships between sibling views and the parent
    layout, but it’s more flexible than RelativeLayout and easier to use with Android Studio’s Layout Editor. Using
    ConstraintLayout, developers can create responsive UIs that adapt well to different screen sizes, orientations, and
    resolutions by specifying constraints that make views dynamically adjust.

42. Difference between RelativeLayout and LinearLayout?
    Linear Layout — Arranges elements either vertically or horizontally. i.e. in a row or column.
    Relative Layout — Arranges elements relative to parent or other elements.
43. When might you use a FrameLayout?
    Frame Layouts are designed to contain a single item, making them an efficient choice when you need to display a
    single View.
    If you add multiple Views to a FrameLayout then it’ll stack them one above the other, so FrameLayouts are also
    useful if you need overlapping Views, for example if you’re implementing an overlay or a HUD element.