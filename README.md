
# Contact Manager App

## 📱 Project Overview

Contact Manager is a simple Android application developed using Java and SQLite. It allows users to save, view, search, update, and delete contact information through a user-friendly interface.

## 🎯 Objectives

- Store contact details in a local database.
- Retrieve saved contacts using a contact ID.
- Update existing contact information.
- Delete unwanted contacts.
- Display all saved contacts.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Development Environment:** Android Studio
- **Database:** SQLite
- **User Interface:** XML
- **Platform:** Android

## ✨ Features

1. **Save Contact:** Add a new contact with a name and phone number.
2. **Search Contact:** Find a contact using its ID.
3. **Update Contact:** Modify the name or phone number of an existing contact.
4. **Delete Contact:** Remove a contact using its ID.
5. **View All Contacts:** Display all saved contacts.

## 🗂️ Project Structure

```text
MyApplication/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/myapplication/
│           │       ├── MainActivity.java
│           │       └── DatabaseHelper.java
│           ├── res/
│           │   └── layout/
│           │       └── activity_main.xml
│           └── AndroidManifest.xml
├── README.md
└── build.gradle
```

## 🗄️ Database Details

**Database Name:** ContactsDB

**Table Name:** contacts

| Column | Data Type | Description |
|---|---|---|
| id | INTEGER | Primary key, auto-incremented |
| name | TEXT | Contact name |
| phone | TEXT | Contact phone number |

The application uses SQLite to store contact information locally on the device.

## 🚀 How to Run the Project

1. Install Android Studio.
2. Clone or download this project.
3. Open the project in Android Studio.
4. Allow Gradle to sync.
5. Connect an Android device or start an emulator.
6. Click **Run** to install and launch the application.

## 🧪 How to Use

1. Enter a name and phone number.
2. Click **Save New Contact** to save the contact.
3. Use the generated contact ID to search for the contact.
4. Edit the name or phone number and click **Update Contact**.
5. Enter a contact ID and click **Delete Contact** to remove a contact.
6. Click **View All Contacts** to display the saved records.

## 🔒 Data Storage

Contact information is stored in a local SQLite database on the device. The application does not require an external database server.

## 🔮 Future Enhancements

- Input validation for phone numbers.
- A more attractive user interface.
- Contact sorting and filtering.
- Confirmation before deleting contacts.
- Export and import contact details.

## 👩‍💻 Author

Developed as an Android application mini-project.

## 📄 License

This project is intended for educational purposes.
