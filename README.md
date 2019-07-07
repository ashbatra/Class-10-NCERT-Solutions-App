# Class 10 NCERT Solutions App  
#### The project is an implementation of Grid Layout in MainActivity and varoius lists in corresponding Activities of the NCERT Books and Chapters in them. The project also uses .json file to extract the marketing banners above the MainActivity and BooksActivity. The Navigation bar of MainActivity implements dialog box to get Rating for the app, using a RatingBar. The project is a simple content viewing application for viewing pdfs and does that with help of a github directory.

---
## Author and Contributors List

### Ashish Batra  

*All the bug reports can be sent to ashish.extraclass@gmail.com*

---

## ScreenShots of the Project application
#### MainActivity
![ss1](https://user-images.githubusercontent.com/44803205/60764882-2ca14c00-a0af-11e9-9ac3-4bcbae497607.jpg)&nbsp;&nbsp;

The Activity supports a Navigation Drawer Menu having options of Sharing the app and Rating it in a dialog box with RatingBar in its Layout file. The Layout file of MainActivity has a ViewPager for Slider Banner above the GridLayout which has CardView for each subject.


#### BooksActivity
![ss2](https://user-images.githubusercontent.com/44803205/60764903-80139a00-a0af-11e9-921e-ccddfae894e1.jpg)&nbsp;&nbsp;

The Activity supports a back button to go back to the previous activity. The Layout file of BooksActivity has a ViewPager for Slider Banner above the GridView which picks up the data for each subject's books from the BooksAdapter.java file of Models folder. The adapter uses a format of book from java class book also present in Models folder.


#### ChapterActivity
![ss3](https://user-images.githubusercontent.com/44803205/60765202-0fbb4780-a0b4-11e9-8b7e-3227bfa4025b.jpg)&nbsp;&nbsp;

The Activity supports a back button to go back to the previous activity. The Layout file of ChapterActivity has a ViewPager for Slider Banner above the ListView which picks up the data for each subject's books from the ChapterAdapter.java file of Models folder. The adapter uses a format of chapter from java class book also present in Models folder. The list items have a layout specified in layout folder by list_item_layout.


#### PdfActivity
![ss4](https://user-images.githubusercontent.com/44803205/60765219-45603080-a0b4-11e9-9a3f-02e24bca5b1a.jpg)&nbsp;&nbsp;

The Activity supports a back button to go back to the previous activity. The Layout file of PdfActivity has a PdfViewer covering match_parent x match_parent. The .java file of the Activity uses a function to load pdf files saved in assets folder of the project. The activity imports a github library implemented in build.gradle(Module:app) file of project.

---

## Gradle Changes
Include the following library implementations for opening pdf and for opening banners in activities from .json file.

```
dependencies {
    ...
    
    implementation 'com.squareup.picasso:picasso:2.5.2'
    implementation 'com.github.barteksc:android-pdf-viewer:2.8.2'
    implementation 'com.google.code.gson:gson:2.8.2'
    implementation 'com.google.android.gms:play-services-auth:16.0.1'
    implementation 'com.google.android.libraries.places:places:1.1.0'
}
 ```
 
Click on Sync Now on Top Right corner to include the above dependencies in your Project.

---
## Acknowledgements
* ExtraClass.com
* Github Barteksc Library
