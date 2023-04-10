# My Helsinki Events 🎭

MyHelsinkiEvents is a sample app, built with Kotlin. The app for keeping up to date with the latest events in Helsinki. The goal of the sample is to showcase the use of ***clean architecture*** in Android.

## Screenshots

The purpose of this project to showcase the use of clean architecture in Android.

## Features
This sample contains 2 screens so far: the events near you screen, and the search screen.
 - **Events near you**: Display a list of events near you.
 - **Search**: Searches for an event by name, language.

## Architecture
The app connects to an external service, which it uses to fetch the Helsinki events. This service, [MyHelsinki Open API](https://open-api.myhelsinki.fi/doc), offers up-to-date information about places, events, and activities around Helsinki. However, this service will be [taken out of use by June 2023](https://open-api.myhelsinki.fi/) due to economic and technological reasons.

### Package structure
This project is organized in package by feature structure. Everything that's related to a feature, and only to that feature, is stored inside the same package. Code shared by two or more features is stored in separate common packages.

Each feature package will have three main packages:
 - **domain**: contain all the use cases, entities and value objects that describe the domain of the app.
 - **data**: this layer responsible for enabling all the interactions with data sources, both internal like database, and external like a remote API.
 - **presentation**: the Android framework (setting up the UI and reacting to user input)

![Package Structure](https://lh3.googleusercontent.com/21aAIkGbfSSDPLd33fpqytBeyuSOwVznaK5hNCMy7lVmJ18ti6ydWeUZmLD6_A_xFNOsMMKXasm6oqKjk-3zK_Fl1EcmlhGrTvZkV79Keeuo795gR85Kd2yXAqFoK61vdZ6UqAXBMqiGr9b8CpHRk8A7uN0y3Se14l-or5TH8m5frfs3II5R8OHVv7ZmyuXscqSozV1scPWCynPJOUvHTl6WuEPAXx1zN4_5a40v663qe_HC4kXz10vcDDIf4a1EVh02twbaIvsOACICXiHC2uDycQIZ49vnrvs0j1JETzBiq5Nnlv5bieR4lUvwuLUVePUnYxr52enuDr02gAWOqGVTEcGm4P104FHbp6B9fvi7_tBwGTVimdatqJPm--ZI9cN4BRzedJT3o0zlbZETHzgu40C5k0tbRh-9UK1z8jc96La-YLXdIbUhfmAcOdWhGAroke6mZ9rsw2y-FH6M7ocnqLEprTVSzDM4M7sRxY-mpbvxJjJJWGCsXb8OF5btyNKal_EgyWmNPSrP9jnJo0a3-mzYzHmqRF1ogO3N2V6taMAokcF0NQgQVnAEgatbxY81g0Cq47GkYjQsGKqcfb3jOUQxkxuj7jK1L15bgP7MoQOLrEqwnY6VfueSzphPMRZLMqcoT6VVEmlXD3krrSC-6Mige8mrn5cWaWf0QYG558F92n7xD2KOcAOUsTsitz5jhVPGOmeJz-hglhBVtZtjmjASVvCx_PCY1DOmN1bTFSqbBsQ-1YVnA_zpnx28qghcBhvbU-VB0Vus0USwzzI-xlZAxpFz701QTC73RV-waYlw9-k39qjY6C5cN_zFtnZHYmclK8tHiAERrd6xYeWRJf-ZDbkzL3MJws3tEZ1QAsrGpXOlrA6tDxztmcMijQPCZN9-8CCfFK-vMEROGqUdIZd0bec23cOTTx0iCTzy_fsDxAqlXjgqwYwKaJUYW4TyiMq91fH_TnYVY4q2fC-M8NSABBYvHpAiIDD7KHIimzT7VoQF0OOK=w2764-h1608-s-no?authuser=2)

---
### Domain layer 
This layer encompasses the business logic of the app, and protect the app's logic from external changes. There are two packages inside:
 - **model**: where all entities and value object live.
 - **repositories (common package)** : where repository interfaces.
 - **uses cases (feature package)**: each use case should only have responsibility over a single functionality.
![Domain layer](https://lh3.googleusercontent.com/F3R0ax8qEUjRW8qKQvZmD55h29wlfsJJ35RYsJqjefaYqe5jZKedlFAvXphHjhiSn-OkV0Sh-92Q_kp5YZHTPmU6rWYNPhJ8E6PRXBlhr0rG4JpI_969M2eBubMUTlkTyk3s5s3nu76DwEeYgA0QxhBuAfSfuzFwSPQjh9fYrheaEfdNf2BJwNzRXFh65K2ZSdQE4dDpKSWbCFA7kWIpz9Tl0Z64rmWNaJf1DT1_7nKMm4quhWN6mTewA-ZeE7o0VMMrhk0fd4bR_KHPpLTlVugnBKKhoBzRDF1NW5EIKqkdvGnYmjqXcOoTAjpXMTHQmcZYbjq1BO-B2SQgYWhOIs0tOkYu7Acuhj0iJN5hBfFQ9OpizHEar-6yDP28gBUtRluihce3-kBWvGoLb_rNApVGh-N3beEzqoaR6eysDFFIBUqJoLGTJdC4g9x6KwMq3SkjQo5uLvMQBlt4VbhNbl0plJF9WxUcm4skqdUoT4n15W5F5vDUw9lUmyu4v5gOO1bLD0c0lu9DWWmZzlyxjmvINdyty9novVr5LS70cSK0_VbDQUcdzd_J9K5qQTHZiKNTf5Rrz90iekF3Z99ONtmpV8tASFq3wd0BeNtY2gke0PpfdIYtXBTPQm6xc6cwJZdxKDdodVdGgAPYOy1IN43UTmAE2s7kMJ2eqDgho8GVX5Wb_lcwE_EdXQRTijkn3xW-0ACtnE9662YW9h0kpKagr42bqG62srnYzSYJMZdJPMI8eAq6tXPfXQzViSKNb3pk33w3Y4mMGn4gnps8Pr-o_nxCPBaeTAqbkRs_BplaHRKMRcxr4sZhwkcy0c6SfJu-xvmfLpSXKvkpxOZUe4Qz458tuZEomnAc90pWLoSBTcE-fjEab9pBf4I2q14NQM_4oQenD1YxqWqBH4SETpUTGkL-xrGjSqz5fw3d_0g-WbpLB7lhzkc9eZFz3YlafOl-ff6k3gU-5UrbKxffVJJu5JRpeVtjDBQgqjyO9pLbgUdF_B64xo8l=w2124-h1248-s-no?authuser=2)

---
### Data layer
The data layer is responsible for interacting with data sources: database, shared preferences, and network. This project follows the repository pattern recommended by Google.
![Data layer](https://lh3.googleusercontent.com/frEaeLoXtnLMW1z4TwBloCoG0IMBIZzyctFiLkVpJIp943LG6ixOke8jHqRC2ylzE-6UdfoRJ5XsTdefBenFwVBvihWqFD-R2kA-1Ure8nKD9q9DpJO3in8GNetsrO9MgOSmV3W9MblMLebVw5xaK4gZtIT4HwgpHZ3pWTJsRdmOFgx0KQ2zf4AUSFkJye3cfveJRzkQTZQ9DCv62V-hEPgiqNdyVKYBgTIoxRmvA9pgzvEoleKYLlwYpLB85WTL146GWOiCLm5UQhHaFj8oIU1weCUdhoRGw2khwfTG1yr7balTG43uL1_NulCXGEZ2oWLKlkKYQkod4rV-Eyv2o9YGuMmwAjevIJZZsM_PPP8Yi7I7AEFwb9F43Tjk8K06kKS6AHuiaAt7t-4wW2N1n4Cw9TURZv8Xv2FjtasviMkPH_nmRuLv7tx_n-rBIGs9iwZpOJj_UP3FCFIriYks8sAvVtuxdI0dUi5m8fWcZijcUABwhu_osy4g3QzoifH2qR3-T-vEclj91pBX-R63x-S3ncxtwqcKpaYSeJqLZp4XiJZtkNBgfQhiJz8dgvpcNlAPJVI_AnqZ0VBmzeKQQOCF_2kunrloalJhSg2bOW8yAb-Pu89Hg9RXRvp4NMipAklXRSFOjPoYsk_rmh_h0DsN6nHLeFdTaZWaDXEsmvImSocagJRNla9M3OaF9kYpz24xJJY0mQUnsdCuvJQCLm5r9v4h-Lrgx7_Rmytg479MXdbOYO1qW426oAHhdcvy27hvtJBxjugmuR581zuNifCubeMECauX-1ilENOZaKw-JKX-qJsNp77nN4QYdUvzTa_RhvbyxlLjhYPjNSjLrHB16avy-PxxkZa65GntWxubeDOuD--qyumfAtwop2s4GSfYI6O_Vvktd9Wc2ak_7XI0rh6sZiL34Q4YU9xTiNwgYRzstaO1cOlJ0kaCeeRiqCn-ukPEO8RQA8x8OIRgUtnwIZ5XBJT4qd8YoaYfrYawSLcfgX_P2VrX=w1684-h1288-s-no?authuser=2)

**Network**
This project connects to the API with Retrofit.

**Caching**
Room is used to create the caching system. The entity relationship diagram is:
![The entity relationship diagram of caching system](https://lh3.googleusercontent.com/FaKuP_Q3309MF9hw3D2-xKzdLbzT1gX9zrXw-5Y7EJqn1NShhU4uaZ8dLeiweUNZ28ixJXHYUYppjtwhagDdmv_uOt1RiD125w0qr-Wyqt6ZopdROuT_XiTbBqW4ivR9Cvw1Nr1T0Wkf5utUUfyiwsnkBZvNaEOyi6ziHGzmIy9qOAWXkTwxwbkx15Af7JUSfElEjupBcrMs2usuzQduo9KwIjZxHMnODIldriat8jnIHQq4q9mfiO_fXA0vV_3rTIFBXn1_J1OjJJ0Cn5_r5pHHpqNwZwy8v4IvF4ysyJQzgmKr1YNxMoFnyVMYOlES3clWmCQ1Vq4MFqRKd-U4KARsffF_KzQFVco1NWqT41CI4dJrdj0lAvcJsUkymmB3UmA8LNFpz4A1JPukBuvZqxoc7Z9VFD3anwhVLF51sNcvwV_hNalKUm1-sm4swB33nnahTKWcUzSOBMH47oP-YbUtHug5AjngYH0eJEykM5yaeIy-6FVn8QvWkfjpNKjE7NQoK4XCZ6nDu2095fL2qtfQKiZ9wkWpo7H6Bqq6EVVUTT5IqoJYxINX1M8uKkmvCxOpCt8xaET668MBr4VFXSd7kE1j7ZPHyMf7I-7huvuN1vxgrxry_2AEB8Krzni2ynXm9y9225jq7n85H235j_3_D5VYUAg1HW4zLpq8Vb17iKtsfoyaZbysmMYsEb_GfvXI_XMVEyfmiq029q5CZbanCHufCcPq6HJflht0LTEfABjl9vjEELQ6x0yNUSomkNsiDDmLm88wYBFHvAfGv37NrphfyuSSSLGDUqCbR9S0uSSJSRfH68KidELvdMqhEl_1Zkb6jMjxU7ATECI_mQFBifkeui2P63UximMtYJrIuGf4gIVd1F6mdjacciQkwrQWb2Iy6fiEF3k6mpMPMOT3LaFLl-yjyQasj0su5gQtody1tpMP44q7xCJS0v3Py4SUzDgE973_uQzZ4SEZ8BADd4l--ydiehdVM0y8x3rJK7j6H4S_jUGA=w1844-h1608-s-no?authuser=2)

---
### Presentation layer
This layer encapsulates all the code related to the UI, holding all the UI-related components. In other words, this layer deals with framework code.

## Data
The events data in this sample is fetched from [MyHelsinki Open Api](https://open-api.myhelsinki.fi/doc). The app needs to work offline, so the data will need to be stored. Both a **cache data source** and a **network data source** will be used. 

The approach is to follow a **single source of truth** implementation. Keep the cache in sync with the network, but ensure that the UI accesses only the cache. This way, the app displays the latest data both on and offline.
