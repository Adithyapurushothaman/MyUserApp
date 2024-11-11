Android Product List Application
An Android application built in Kotlin using MVVM architecture to display a paginated list of products, fetched from an API, with a detailed view on item click.

Features
-MVVM Architecture: Clean and modular code organization.
-Lazy column is used for the list view of Products.
-Network Requests with Retrofit: Fetches product data from a remote API.
-Pagination: Loads products in pages of 10 items each.
-Detail View: Shows detailed information when an item is clicked in the list.
API
-Data is fetched from Dummy JSON API with pagination parameters for limit and skip.

Libraries Used
Retrofit: For making network requests.
Paging: To handle pagination.
Jetpack Compose: For building the UI.
Coil: For loading images.
