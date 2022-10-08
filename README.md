# Rijksmuseum Gallery

An application built on Jetpack Compose to scroll through the expositions of Rijksmuseum. It
implements Material You theming and pagination.

## Technical Stack

* Kotlin
* Coroutines
* Material3 API
* Compose for UI
* Paging3 for Pagination
* ViewModel
* Hilt for Dependency Injection

## API Key

In order to access Rijksmuseum API an api key is required. It can be obtained
from [Rijksmuseum web site](https://data.rijksmuseum.nl/object-metadata/api/)

Then you will need to create object with name ApiKey in the root of the project (same directory
where the App class is located) and put a const value API_KEY in it.
```
object ApiKey {
    const val API_KEY = [your key]
}
```