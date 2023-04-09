# My Helsinki Events

The app for keeping up to date with the latest events in Helsinki.

~~screenshots~~

## Purpose

The purpose of this project to showcase the use of clean architecture in Android.

## Goal

The goals of this project are to learn how to:
 - Build features in a scalable way, covering each architectural layer,
 - Modularize the app,

## Features

This project includes the following features:
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

|  |  |
|--|--|
|![enter image description here](https://lh3.googleusercontent.com/JbUjrqoVEOavTWPpVjsbCuDsak2fK93ZewzR9Iv2s_5dmGqm0K9dWU6wPu5D8gstifqi9eQHuxJkau3yNpgp4HNGpyatRWEUpuVqnW_9d4GuWSkFuz0I-e-oIoMkDLy24QMxwk6m3A7WCwdzsjJlgNa8n9QbaKBInUh-26Frl5HIlvmD3EVJcMqdvYyrgdtwy3iEs_idn7e_w11s9IdKsa199ll1_oGW4eb2o6uxjFb3FC5QSIHvbq7JCenQhoQroLz6j3ohygwPAfwAg0OKUUKQXcW84BVtgpIToYiSnePqWHgSeGbHfWQ5XGNj-74B9BKk78pBG4E9Yl7PqfpiAkFTd6UXFr9hQYTvefTC46LzC0b3In6LY6rAdbAJbmBH5C81wlaLefQnH7UyH3Sjjplzidp6VLEEkd_fHJVz5d6COe8a9TUz_1ljbexzWG05NL-2vaCDxk9gJ3mre5CcQjeHjpMqOcUBhEK2T0dkjRnVhC3_1mB61q39p8qyCH-gLWw--n-bhnY2-BHT0UX0lIrsgreQ0u1oYwTCeaTKjKaL-xy0D6aVZPg3A1y9atep5iMXu6mSNSEet9fgdltd4Wbnr3GJhK3izvrYqvb-gwtxOYluQUPXBL_FFvhcJHULfKzgXJalCFH75A6xJHIYU55c6L_h8R1IMWzNF-a5VjhHphtEhg0MZvFGVZqSqdsm16skuXLCsrTb08DWqUbHEf2cROeplLAFJaoESN2JZuKRsCZt3ydnmO2iDRWDu4yVoxRnsxBL4j5NVX37l6tP-tcVAGTbi6Yd7IF5sySIadZiu2FDCMWEgaH8s0rmVarcAmFnhbmLsUrdH6HvwWWZ8JGTr8e3Vho1rcfbjT4BalB_2Urv0r0N34Do7_c9oZgC6rO9KtIKjgAKLuJlni3k9K-is8ewpg8zDrAcr9VwxEJSokGNzGDFtsCqvmM4zu9WxFzcaG2ws8j1vQKks-2d8dorp0ujMIL6iW40a2I3lS9aX2Qy9uy7AA8e=w436-h634-s-no?authuser=2) | ![enter image description here](https://lh3.googleusercontent.com/lwRqwmvlO6DXkUqQU8ID_zjO7AFu3JrbenHhYVKn4n4Y-bw79aH0Hp9gykWRLSAEHjA1BL2jzjpMr9v8OxsF9-UyRy6-MP-pOtJppmRDjZCbuIBvIEFyK_ijv-VSmTQoTSG_kVLQ6c-Fc-N0E8m_FY2CDgZBK8MHSvRSO1HSFXUFfOnH_VIvPTb87719Twb_RxKuyH54mMkIRpNIlQqQwLGvqQ0PGdJubAuIzolykkOADQbG3zCDUQZicEhi00QtuB-dTp5-vrtvoVhG8cfZDN5mOOOdZonqkQLTDf7cuqoWRLVKv5nCIMEJ_pJUo9ACLUQWJqLhYbS5E8gDiNLEXr4dH9IArnT33v_2DxN5way75M7JSjXViqlSsd1IgfKbiESMvnL4NFGHZ7zHrKP_MQddJKVYHOn-kVTwJ6fvZFUdsUsRH2z51raTcCR9mH9gV7-EcWJfpZXkC6z0B2i-KIzoQnxMIZzHU7OvpP7L8DLsqnTJFzaGFC0IcQ9VCR1hKhrm-HCVNaitIZ4eY7dR1DwQU5I3pLX3rIMbivx9Uoqb9ojv1s_hfaqcuWE_ToF2Wemqa4iz1jkbWhixPFcnxmNB7weH-0nPTC7qVtFK49D0jAToDTIrhU2z1YqyJnVwdcxSEY8CSVrsZ7xFAULJFTlyS5hptqBKzEZefguBv8Foy2zTh47bP-z39H6eoZZfZkM0dSPGm_ZE5KpmDNfKRgGlEFLFKok4asP0Wug1qqvgiddjSg7Y80ga6QeF-0SJmmQCTfa6A2cfKnxc4h64xF1TGbsGAJ_GgMVzLqQcBzg8BHXzzgaEmIWxn2VF2D6xrxEWaXctMpl7337HByuu2ZDU28Kp3RCPkeYioVl09uimMAWAOYrw_0d5XoyOeOjjbZQeP9ntubU6j1pPVZn7MF1XdXJtaWQ9hb4P_I8-W-TBnhCTQxDgnqricP0tDGy8h-2wWowPTuro10VEmpkkS4HeoGcLk4_npPGSYvhZsQxQEfuLm8WbF1s3=w640-h1278-s-no?authuser=0 =300x600) |


### Domain layer
This layer encompasses the business logic of the app, and protect the app's logic from external changes. There are two packages inside:
 - **model**: where all entities and value object live.
 - **repositories (common package)** : where repository interfaces.
 - **uses cases (feature package)**: each use case should only have responsibility over a single functionality.

![enter image description here](https://lh3.googleusercontent.com/EZ2pfw4w5y01OucAJG7tCHeEUEs-K0JkAILrznO8UNsw_9W_s78VoTdYnvX5XdyDrsPbCn7ATWg3yGbCdp44j4Hu86TZmg9Mi__FrS_44tXw9w1qQvMMurosr1EWBTGg6zJoCZ8adSAp9Eii1Y7QA5uBRbcar0d2Gc08daOCRM81w083EnvubnRLVQClEIvJhCqrhl9CcngOWO0mdN8ymKLU7r7_1lBUx3E51UHm6k05HT7h814KKEAbDI0NVEgAHlSKdvKdf_QLziVOFxIRrnPbEF9dVq4oheHw6p8MS5d6p4DJgeWyeWPFaO0gfqyUo-y25DVBkqrVrzSiGomY2lPwKrfkiAeZluCCJzAeQj7a57N5a8OsclQxeaz1koZ_ptJg3sIIodwsAFf9-OGQ12v-Gu3aIABUwM64LPhyGRucLvV7TWDikvoBw-zFxp9GkcM23G2LYwi2zYKRkJO95un2xdxQe9GFJofu_hxXl6Kp7REjg2diEitzc8t_kXLTTYHAdCTuZOzwdZ_GY1bv36w28f5n1aYlnbgEPjAIWaax3mR-h2TZFpdBZROiQpcW80Ab91UyxiQaf5-aTKrOmevmPMZENeo5Ytye76IMH_rPFUtir96dIfrS_R06jI4bSojmJlYp5s4mKEuaw0vYooM4su9VVt2lhqCPrZg4AgKnEXMU-ZEYacPTmEhEwcZrEm7IAbx386RO-jKZD_g_-OpAGcVDpmYCqrQs_2IJ3ZiblrZUoc0P0rBiVZwZ3EG3pfqgXOEam5Y3U3DP4BgRRzKBYV59ocjemxAZAvA4uRww4yPunCZA3FE7RSnMEIbfS_yzsMBF1ASu-KSjPRNhbmtJCVrULdNw5sjkQJNhVGq8Kr1LI6BXczS1_6-mG4CxC-cdV7QP3-SMtwlpE_GE4aq1R6Rrpj8Gk3cO94E6df6Mptc2j54gm41-88W9a-4HWQk8oYuCx8zc0jH8g93U7gn2l-4pSEhO1mFwvKP6ye7_Cw-Rse6JTTRV=w1964-h1044-s-no?authuser=0)

### Data layer
The data layer is responsible for interacting with data sources: database, shared preferences, and network. This project follows the repository pattern recommended by Google.

hinh du lieu chuyen tu data sources -> repository implementation -> repository interfaces -> use cases

hinh https://developer.android.com/topic/architecture/data-layer

Network
This project connects to the API with Retrofit.

hinh tuong tu https://developer.android.com/topic/architecture

Caching
Room is used to create the caching system. The entity relationship diagram is:

hinh lien ket database

Presentation layer
This layer encapsulates all the code related to the UI, holding all the UI-related components.
