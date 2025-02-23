# Johnny - Task Management Chatbot

Johnny is a command-line chatbot that helps you manage tasks efficiently. Inspired by **Cyberpunk 2077's Johnny Silverhand**, this chatbot ensures that you stay on top of your schedule while keeping interactions entertaining.

Johnny supports 3 different types of tasks:

* **To-Do** (just description)
* **Deadline** (description + deadline by which the task must be completed)
* **Event** (description + start time + end time)

---

### **1. Prerequisites**
- Ensure you have **Java 17** installed.
- Verify installation with:
  ```sh
  java -version

### **2. Running Johnny**
- To launch Johnny, download the Johnny.jar file on your local computer, navigate to the directory containing Johnny.jar, and run:
```angular2html
java -jar Johnny.jar
```
You’ll be greeted by Johnny, ready to take commands.

### **3. Commands**
Command | Description            |Example
|----------|------------------------|----------|
todo <task>| Adds a ToDo task       |todo read book 
deadline <task> /by YYYY-MM-DD| Adds a task with a deadline |deadline submit cs2107 quiz /by 2025-03-01
event <task> /from <date> /to <date>| Adds an event with a time range |event attend a party /from 2025-03-03 /to 2025-03-04
list| Lists all tasks        |list
mark <task number>| Marks a task as done   |mark 2
unmark <task number>|Marks a task as not done|unmark 2
delete <task number>|	Deletes a task|delete 1
find <keyword>|Finds tasks containing the keyword|find party
undo|	Undoes the last command|undo
bye|Exits Johnny|bye

### **3. Error handling**
Johnny throws shade if you mess up. Here are some common mistakes and their responses:
```angular2html
hi
```
Johnny Says:
```
"That supposed to be a command? ‘Cause all I see is keyboard smashing..."
```

### **4. Testing Johnny**
To run automated tests, navigate to ip directory and run:
```angular2html
./gradlew test
```
It will output whether build is successful or failed depending on the result of the tests.

Before running the tests, make sure that your initial storage with tasks is empty.


