# Seekho-AnimeApp

A simple Android app that fetches and displays a list of anime series using the Jikan API, allowing users to view details for each anime.


📌 Features

1. Fetches Top Anime from Jikan API
2. Anime List Page displays:
  Title
  Number of Episodes
  Rating (MyAnimeList score)
  Poster Image
3. Anime Detail Page displays:
  Title
  Synopsis / Plot
  Genre(s)
  Number of Episodes
  Rating
  Poster Image
4. Handles network errors gracefully
5. Clean MVVM architecture with Retrofit, LiveData, and Glide.


⚙️ Libraries & Tools Used

1. Retrofit – For API calls
2. Glide – For loading images
3. LiveData / ViewModel – Reactive UI data handling
4. Coroutines – Asynchronous tasks
5. RecyclerView – Displaying list of anime
6. Material Components – For modern UI


📝 Assumptions

1. Trailer videos are not shown, only poster images.
2. Only essential details (title, episodes, rating, synopsis, genres) are displayed.


⚠️ Known Limitations

1. Cast information may be missing for some anime due to API data limits.
2. Pagination of top anime list is not implemented.


🧩 Future Improvements

1. Add offline support (Room).
2. Implement search feature.
3. Improve UI/UX for error and empty states.
4. Add pagination for long anime lists.
