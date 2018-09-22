# ShopiCart

## Shopify Mobile (Android) Developer Intern (Winter 2019) Challenge

### Summary

This repo contains the code for the [Shopify - Mobile (Android) Developer Winter 2019 Challenge](https://docs.google.com/document/d/1jxo50o80I1UeowrRtz-SG_ujpET4nvSVW4bDDjJ2V34/edit). In this Android application, an [Shopfiy REST API](https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6) call is made to get the product details. Product details are iterated and grouped via tags. 

**Tags Page:** It would display the list of unique tags in the product details response

**Product details page:** On clicking on any one of the tags. All the products which contain the tags are shown in the Product details page. 

Product details page contains details such as *image of the product, product variants, total available inventory of the product (across all the variants).*

### Technology

**Languages/Tools:** Java, Android Studio

**Libraries/Frameworks:** OkHttp3, Picasso

### Architecture

![Architecture](https://github.com/visakan4/shopifyWinterChallenge/blob/master/readMeImages/architecture.PNG "Flowchart")

### Output

![Tag List](https://github.com/visakan4/shopifyWinterChallenge/blob/master/readMeImages/tagList.png "TagList")
![Tag Seach](https://github.com/visakan4/shopifyWinterChallenge/blob/master/readMeImages/tagSearch.png "Tag search")
![Product List](https://github.com/visakan4/shopifyWinterChallenge/blob/master/readMeImages/productList.png "Product List")
![Error Message and Retry](https://github.com/visakan4/shopifyWinterChallenge/blob/master/readMeImages/ErrorMessage.png "Error Message")

### Setup

 * Install Android Studio (Minimum SDK version 23.0)
 * Clone the repository `git@github.com:visakan4/shopifyWinterChallenge.git`
 * In Android Studio, File ---> Open ----> Navigate to the file location
 * Build the application
 * Run the application once the build is successful.

### Credits

Icon made by [Freepik](https://www.freepik.com/) from [Flaticon](www.flaticon.com)
