# Ubiquity Catalog Assignment

## Build
All should build/run out-of-box, at least in Android Studio.
Open Android studio and run it.

## Libraries
### DI - Hilt
Easy to use, cover all need for this project and pretty popular
### Image Loading - Coil
Easy to use, cover all needs
### Networking - Retrofit + OkHttpClient
Same, easy to use, cover all needs, super popular
### JSON parsing - Kotlin Serialization
Native, works out-ot-box and allow custom mapping in cases when JSON object structure do not match
DTO. E.g. [ProductDtoSerializer](https://github.com/devepi/UbiquityApp/blob/daa79c2bbb551823f5e267cfdfaf11ba7c0e2aa8/app/src/main/java/com/example/ubiquiti/catalog/core/dto/ProductDto.kt#L47)
### Compose
Unfortunately, I don't have commercial experience working with Compose. All I know about it is from
pet projects and small freelance projects. There are few known issues in code. With view binding 
I think I could provide better results. But today, probably, there is no point going
back to view binding. 
### Navigation + Compose + NavType + Parcelable
Easy to set up and easy to pass objects between screens, requires a bit of tweaking like
implementing NavType. Aside of that it's probably the easiest way to do the job.
### Testing
There are a couple of unit tests to make sure JSON parsing works. Regarding, espresso test - never
done it with compose yet. Only with view binding.
### Async work - Coroutines
Used coroutines here, more than enough for such project. Could use Flow, but again too simple 
project, no point in using flow. The only place where I see point of using it is to build 
functional dependencies between filter and products(on filter change products should be filtered).

## Tradeoffs
I think I implemented almost everything I wanted, except few things, no more time left:
- String resources - started without it and had no time to fix it
- Product line view - does not look like in design
- Espresso tests - no time left to dig in
- Compose - no time to dig in deeper
