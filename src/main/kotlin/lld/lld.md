Low-Level Design (LLD) is a detailed design phase that focuses on the internal workings of modules, components, and
methods of the system. Here are the main things to consider while performing LLD:

1. Class Design
   Class Responsibilities: Clearly define what each class will do. Focus on encapsulating the business logic and make
   sure that classes have a single responsibility.
   Attributes and Methods: Define the attributes and methods in each class, and ensure methods are designed to be
   cohesive and encapsulate functionality logically.
   Access Modifiers: Use appropriate access modifiers (public, private, protected) to control visibility and enforce
   proper encapsulation.
2. Data Flow
   Data Structures: Choose appropriate data structures (arrays, lists, maps, trees, etc.) based on performance needs
   like time complexity for access, insertion, and deletion.
   Flow of Information: Map out how data will flow between modules and components, ensuring it is clear how different
   parts of the system will interact with one another.
3. Interface Design
   API Design: If your system interacts with other systems, define clear and minimal interfaces. Consider REST or
   GraphQL API designs if applicable.
   Contracts and Dependencies: Clearly define the contracts between interfaces and the dependencies between classes or
   modules to minimize coupling and maximize reusability.
4. Error Handling
   Exceptions: Plan for exception handling to deal with runtime errors and edge cases. Specify the types of exceptions
   and how they will be propagated or logged.
   Failover and Recovery: Think about how the system should behave in case of failures—graceful degradation, retries, or
   alternative paths.
5. Concurrency and Threading
   Multithreading: If the system will involve concurrent processing, consider thread safety and synchronization.
   Locks and Semaphores: Decide on the locking mechanisms to avoid race conditions, deadlocks, and other concurrency
   issues.
6. Memory Management
   Memory Leaks: Keep an eye on resource management and avoid memory leaks.
   Caching: Design an appropriate caching strategy if necessary to improve performance.
7. Scalability and Extensibility
   Design for Change: The design should be modular and flexible to accommodate future changes or new features without
   extensive rewrites.
   Load Handling: Plan for how the system will scale, particularly in terms of handling large numbers of users or high
   data volumes.
8. Security
   Authentication and Authorization: Plan for user authentication and role-based authorization if applicable.
   Data Security: Design with data encryption, secure communications, and access control in mind.
9. Testing
   Unit Tests: Plan for unit tests to validate the internal logic of methods and classes.
   Integration Tests: Ensure that the components will work together as expected, testing for possible interaction
   issues.
   Test Coverage: Aim for sufficient test coverage to catch edge cases and improve the robustness of the system.
10. Performance Optimization
    Response Time: Plan for optimizing response time, such as limiting the number of database queries or reducing the
    amount of data passed over the network.
    Load and Stress Testing: Consider the maximum load the system is expected to handle and design accordingly.
11. Documentation
    Design Diagrams: Use UML diagrams, sequence diagrams, or flowcharts to represent the flow of control, data, and
    interaction between components.
    Code Comments: Document methods, parameters, and logic inside the code to make it easier to understand and maintain.
12. Communication Between Components
    Loose Coupling: Aim for loose coupling between components to allow for independent development and ease of
    maintenance.
    Component Interaction: Clearly define how components will communicate, whether it's through method calls, messaging
    systems, or shared memory.
    By addressing these aspects during LLD, you ensure that the system is efficient, maintainable, and scalable, while
    also meeting performance and security requirements.


1. functional Requirements clears 1st and non functional requirements
2. Flow Diagram
3. Entities and Classes {components}
4. Concurrency with concurrenetHashMap and conconcurrenetLinkedQueue for multi-threading and use reetrantLock()
5. Data Structures and Algorithms
6. Content the compoents to sytem. 
7. Analytics components

Check LRU, Malloc, Snake Ladder

https://www.geeksforgeeks.org/top-10-system-design-interview-questions-and-answers/