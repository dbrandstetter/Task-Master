<h1 align="center">Welcome to TaskMaster 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0-blue.svg?cacheSeconds=2592000" />
  <a href="https://github.com/WeidenauerErik/Task-Master" target="_blank">
    <img alt="Documentation" src="https://img.shields.io/badge/documentation-yes-brightgreen.svg" />
  </a>
  <a href="#" target="_blank">
    <img alt="License: MIT Licence" src="https://img.shields.io/badge/License-MIT Licence-yellow.svg" />
  </a>
</p>

> This is TaskMaster, your aesthetic online Homework Organizer.

### 🏠 [Project](https://www.david-brandstetter.com) <- click to view application

## Usage

```sh
To use, go to www.david-brandstetter.com (temporary uri), create an account and organize your company and yourself.
```

## Author

👤 **David Brandstetter, Erik Weidenauer**

* Website: www.david-brandstetter.com
* Twitter: [@dbrandstetter\_](https://twitter.com/dbrandstetter\_)
* Github: [@dbrandstetter](https://github.com/dbrandstetter)
* LinkedIn: [@David Brandstetter](https://linkedin.com/in/david-brandstetter-it)

## Description

The TaskMaster project is a web application intended to manage the homeworks and tasks of a class in a clear and centralized way.
Teachers can log in with their login data and assign tasks to all students.
The students on the other hand can view these assignments in their own account.

Our application is based on SpringBoot using Thymeleaf.
SpringBoot to create a web app with Java and manage the project.
The usage of Thymeleaf is important to dynamically generate DOM elements from the HTML code, which is important for creating new tasks.

If a new user calls the start page of our website, he is offered to register and create a new account.
For this purpose the user enters his room name, username, position (teacher/student: different permissions) and password.
Now he can sign in on the login page.

The teachers can, if registered, assign tasks centralized for their room/group.
Every student who belongs to that room will see those assignments in their account.

As far as data storage is concerned, the user's password is stored hashed with SHA-256.

The goal of the project is to learn how to manage the login of users and the storage of data, as well as to become more familiar with SpringBoot and Thymeleaf.

## Show your support

Give a ⭐️ if this project helped you!

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_