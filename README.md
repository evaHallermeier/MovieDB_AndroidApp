# Movie Database - Android Application

This project is an android application, developed in Java with Android Studio. The app uses a client-server structure and MVVM architecture, it gives the user access to a movie database. They can scroll and find the perfect movie and click on it in order to get more details on it.
This App uses the Movie Database API that provides access to a complete DataBase with lots of movies.

The user needs to open the app, they can scroll vertically and find a movie: click on it and get more details and they have the possibility to come back to the main screen with the list.


<img src="https://github.com/evaHallermeier/androidApp-remote-Joystick/blob/master/image/screen.PNG" width="140" height="283"/>

## Getting Started

### Prerequisites

Before compiling and running, please make sure to have installed Android Studio.
You also need to use a smartphone/tablet (make sure that AVD Manager is installed) to run tbe app.

## Compiling & Running

1. Download the project from GitHub and open it in Android Studio (I used Android Studio 4.2.1).
2. Define as Android SDK Android 11.0 -API level 30
3. Start the App from Android Studio or from your android smartphone
6. Now you can use the app

## Deployment

### MVVM Architechture

*Model*: hold the business logic, return data of a specific movie

*View*: the user interface on the application on the smartphone or emulator

*View Model*: send commands /notifications to the model to get values or notify and update movie list

## Functionalities
*Main Screen*
When the app is opened, the user will see a list of movie: they can scroll vertically to get more movies
Each movie is represented in a cell with an image, the title of the movie and its year of release.

<img src="https://github.com/evaHallermeier/androidApp-remote-Joystick/blob/master/image/ipPort.PNG" width="160" height="105"/>
<img src="https://github.com/evaHallermeier/androidApp-remote-Joystick/blob/master/image/ip-port.PNG" width="160" height="105"/>


*Movie Detail*
After click on a movie cell: the user will see a new window that contains the details of the movie that they clicked on: a bigger image, the title, ratings by viewers, the year of release and an summery of the movie.
