package com.example.javafxcinema_project;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        // Movies information
        String[] names = {
                "Bullet Train", "Five Nights at Freddy's", "Cars", "Spider-man: Across the Spider-Verse",
                "Fight Club", "John Wick: Chapter 4", "Venom", "Guardians of the Galaxy Vol. 3"
        };
        String imgPath = "C:\\Users\\Eduardo\\Desktop\\JavaFX-cinema_project\\src\\main\\resources\\img\\";
        String[] imagePaths = { imgPath + "Bullet Train.jpg", imgPath + "Five Nights at Freddys.jpg", imgPath + "Cars.jpg", imgPath + "Spider-man Across the Spider-Verse.jpg",
                imgPath + "Fight Club.jpg", imgPath + "John Wick Chapter 4.jpg", imgPath + "Venom.jpg", imgPath + "Guardians Of The Galaxy Vol 3.jpg"
        };
        String[] classifications = { "B-15", "PG-13", "AA", "A", "C", "B-15", "B", "B"};
        String[] durations = { "126 min", "109 min", "117 min", "140 min", "139 min", "169 min" , "118 min", "149 min"};
        String[] genres = { "Action-Comedy", "Horror/Adaptation", "Family/Adventure", "Action/Sci-fi",
                "Drama/Thriller", "Action/Neo-noir", "Action/Sci-fi", "Sci-fi/Action"
        };
        String[] descriptions = {
                "Ladybug, a professional assassin, is assigned to retrieve a briefcase from a bullet train. Soon, he finds himself battling many other killers who board the same train but with a different objective.",
                "A troubled security guard begins working at Freddy Fazbear's Pizzeria. While spending his first night on the job, he realizes the late shift at Freddy's won't be so easy to make it through.",
                "Lightning McQueen, a racing car, learns a hard lesson in life when he damages a lot of property in Radiator Springs. His task is to repair the damage done before he can get back on the road.",
                "In an attempt to curb the Spot, a scientist, from harnessing the power of the multiverse, Miles Morales joins forces with Gwen Stacy.",
                "Unhappy with his capitalistic lifestyle, a white-collared insomniac forms an underground fight club with Tyler, a careless soap salesman. Soon, their venture spirals down into something sinister.",
                "With the price on his head ever increasing, legendary hit man John Wick takes his fight against the High Table global as he seeks out the most powerful players in the underworld, from New York to Paris to Japan to Berlin.",
                "While trying to take down Carlton, the CEO of Life Foundation, Eddie, a journalist, investigates experiments of human trials. Unwittingly, he gets merged with a symbiotic alien with lethal abilities.",
                "Still reeling from the loss of Gamora, Peter Quill must rally his team to defend the universe and protect one of their own. If the mission is not completely successful, it could possibly lead to the end of the Guardians as we know them."
        };
        String videoPath = "C:\\Users\\Eduardo\\Desktop\\JavaFX-cinema_project\\src\\main\\resources\\videos\\";
        String[] trailerPaths = {
                /*
                videoPath + "Bullet Train.mp4", videoPath + "Five Nights at Freddys.mp4", videoPath + "Cars.mp4", videoPath + "Spider-man Across the Spider-Verse.mp4",
                videoPath + "Fight Club.mp4", videoPath + "John Wick Chapter 4.mp4", videoPath + "Venom.mp4", videoPath + "Guardians Of The Galaxy Vol 3.mp4"
                 */
                videoPath + "justTurned18ICanFinallyWatchAdultPorn.mp4", videoPath + "justTurned18ICanFinallyWatchAdultPorn.mp4", videoPath + "justTurned18ICanFinallyWatchAdultPorn.mp4", videoPath + "justTurned18ICanFinallyWatchAdultPorn.mp4",
                videoPath + "justTurned18ICanFinallyWatchAdultPorn.mp4", videoPath + "justTurned18ICanFinallyWatchAdultPorn.mp4", videoPath + "justTurned18ICanFinallyWatchAdultPorn.mp4", videoPath + "justTurned18ICanFinallyWatchAdultPorn.mp4"
        };

        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Movie movie = new Movie(names[i], imagePaths[i], classifications[i], parseDuration(durations[i]), genres[i], descriptions[i], trailerPaths[i]);
            movieList.add(movie);
        }

        ArrayList<String> selectedSeats = new ArrayList<>();

        MovieListWindow movieListWindow = new MovieListWindow(movieList, selectedSeats);
        movieListWindow.start(new Stage());

        /**
         * TODO offers:
         * -OFFERS
         * String[] offers = {0,0,0,0}
         * 0 - membership = 15% discount
         * 1 - full room = 2500 -1500
         * 2 - full room VideoGames = 2500 for 4 hours
         * 3 - Celebration Day = 50% discount
         */
        /**
         * TODO: chooseSeats
         * al seleccionar salas llenas:
         * mostrar informacion completa del pago y horas
         * permitir cancelar la opcion
         */
    }

    private int parseDuration(String duration) {
        return Integer.parseInt(duration.split(" ")[0]);
    }
}
