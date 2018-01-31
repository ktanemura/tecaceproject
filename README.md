# tecaceproject
Project for TecAce application

3 Fragments that transition to each other.  Fragments are programatically created using Java reflect using data stored in JSON files.

## Video Demo
Demo of the application recorded on my device
https://streamable.com/d5pwd

## Class files
Class files are found in app/src/main/java/co/tanemura/kyle/tecaceproject folder

#### TecAceFragment
Abstract fragment class that holds core functionality of fragments

#### MainActivity
MainActivity of app

#### FragmentInfo
Utility class that holds the info parsed from JSON files

#### Fragment1, Fragment2, Fragment3
Fragment subclasses, extend TecAceFragment

## JSON Files
Demo JSON files are found in app/src/main/assets/json folder

There is 1 JSON file for each included fragment

## Layout Files
Layout files are found in app/src/main/res/layout folder

#### activity_main
Main activity layout folder

#### fragment1_layout, fragment2_layout, fragment3_layout
These layouts are for each included fragment.  All include a Button and TextView.

Slight variations on button location and text color to make distinguishing between them easier.

## Animators
Demo animators located in app/src/main/res/animator folder

These are used by the fragments for transforming between views.

Includes enter_left, exit_right, enter_right, exit_left
