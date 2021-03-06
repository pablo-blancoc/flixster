# Project 2 - Flixster 

Flixster shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: 3 days, not full days

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current movies** from the Movie Database API
* [x] Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
* [x] For each movie displayed, user can see the following details:
  * [x] Title, Poster Image, Overview (Portrait mode)
  * [x] Title, Backdrop Image, Overview (Landscape mode)
* [x] Allow user to view details of the movie including ratings within a separate activity

The following **stretch** features are implemented:

* [x] Improved the user interface by experimenting with styling and coloring.
* [x] Apply rounded corners for the poster or background images using [Glide transformations](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#transformations)
* [x] Apply the popular [View Binding annotation library](http://guides.codepath.org/android/Reducing-View-Boilerplate-with-ViewBinding) to reduce boilerplate code.
* [x] Allow video trailers to be played in full-screen using the YouTubePlayerView from the details screen.

The following **additional** features are implemented:

* [x] Portrait mode and Landscape mode are supported on all activities, not just the main screen
* [x] For each movie, its' cast appears as a horizontal scrollable including picture of actor, character played and name

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/pablo-blancoc/flixster/blob/main/walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LICEcap](https://www.cockos.com/licecap/)

## Notes

The most challenging part of the app was an error that prevented de RecyclerView of the cast to load in full size, so images would load in a small size and then as you scroll they would grow bigger. This was solved hard-coding the width and height values of different components with help of TA Lance (thanks!).

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright 2021 Pablo Emilio Blanco Celis

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
