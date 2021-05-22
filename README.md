# MFP - Material File Picker

<img src="https://image.flaticon.com/icons/png/512/887/887997.png" height="200" width="200"/>

## Description

**File Pickers** are used to get the files from the local storage for in app usages. This quick and easy approach gives a uses an intuitive way to fetch the files from local storage and do stuff. I have used Material File Picker as an option to route the files from the files manager. Please find the full project and dependency list below. 

## Requirements

 1. Enable Data Binding 
 2. Set the permission inside Manifest File 

**Permission**

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" /> 
   


3. Add material library dependency in app level ***build.gradle*** (comes pre-installed in latest version of android studio)  

**Dependencies**

    implementation 'com.google.android.material:material:1.3.0' 
    implementation 'com.nbsp:materialfilepicker:1.9.1' 
    implementation 'com.github.GrenderG:Toasty:1.5.0'

 

**Add these color labels inside Res -> Values -> Color.xml**

    <color name="primary_dark">#000000</color>
    <color name="colorPrimary">#3F51B5</color>
    <color name="colorPrimaryDark">#303F9F</color>
    <color name="colorAccent">#FF4081</color>

## Sample Video

<img src="sample_gif.gif" height="550" width="270"/>



