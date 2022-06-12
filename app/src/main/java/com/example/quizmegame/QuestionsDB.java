package com.example.quizmegame;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QuestionsDB {

    private static ArrayList<Question> geographyQuestions(){

        final Question qst1 = new Question("Which is the capital of Egypt;","Cairo","Alexandria","Luxor","Giza","Cairo","");
        final Question qst2 = new Question("Which was the first capital of Greece;","Patras","Athens","Nafplio","Thessaloniki","Nafplio","");
        final Question qst3 = new Question("Which country belongs in the Balkans;","Spain","Turkey","Bulgaria","Holland","Bulgaria","");
        final Question qst4 = new Question("Which country is not a neighbor of Greece;","Bulgaria","Roumania","Turkey","Albania","Roumania","");
        final Question qst5 = new Question("How many stars does the EU flag has;","14","13","12","11","12","");
        final Question qst6 = new Question("How many states does the USA have;","50","51","52","54","50","");
        final Question qst7 = new Question("Which is the capital of the USA;","Seattle","Washington","New York","California","Washington","");
        final Question qst8 = new Question("Which is the capital of Canada;","Toronto","Nevada","Ottawa","Ontario","Ottawa","");
        final Question qst9 = new Question("Which is the national animal of Scotland;","Fox","Unicorn","Horse","Dolphin","Unicorn","");
        final Question qst10 = new Question("How many countries are there in the UK;","3","2","1","4","4","");
        final Question qst11 = new Question("On which continent would you find Lake Vostock?","America","Asia","Antarctica","Europe","Antarctica","");
        final Question qst12 = new Question("Out of these four capital cities, which is the most northerly?","Moscow","Tokyo","Ottawa","Helsinki","Helsinki","");
        final Question qst13 = new Question("Where in the world would you find the largest volcano?","Hawaii","Russia","USA","Nepal","Hawaii","");
        final Question qst14 = new Question("In terms of area, which is the world’s largest landlocked country?","Kazakhstan","Mongolia","Egypt","Paraguay","Kazakhstan","");

        final ArrayList<Question> questionList = new ArrayList<>();

        questionList.add(qst1);
        questionList.add(qst2);
        questionList.add(qst3);
        questionList.add(qst4);
        questionList.add(qst5);
        questionList.add(qst6);
        questionList.add(qst7);
        questionList.add(qst8);
        questionList.add(qst9);
        questionList.add(qst10);
        questionList.add(qst11);
        questionList.add(qst12);
        questionList.add(qst13);
        questionList.add(qst14);

        return questionList;
    }

    private static ArrayList<Question> entertainmentQuestions(){

        final Question qst1 = new Question("What was the first feature-length animated movie ever released?","The little mermaid","Snow White and the Seven Dwarfs","Cinderella","The sleeping beauty","Snow White and the Seven Dwarfs","");
        final Question qst2 = new Question("In The Matrix, does Neo take the blue pill or the red pill?","Red","Blue","Both","Neither","Red","");
        final Question qst3 = new Question("In what 1976 thriller does Robert De Niro famously say “You talkin’ to me?”","The godfather","Die Hard","Mission Impossible","Taxi Driver","Taxi Driver","");
        final Question qst4 = new Question("What is the highest-grossing R-rated movie of all time?","Joker","Guardians of the Galaxy","Pulp Fiction","Deadpool","Joker","");
        final Question qst5 = new Question("In what 1979 James Bond movie does the famous spy go to outer space?","Moonraker","From Russia with love","Goldfinger","The spy I loved","Moonraker","");
        final Question qst6 = new Question("Joaquin Phoenix received his first Oscar nomination for playing Roman emperor Commodus in what 2000 Oscar-winning epic?","Pompei","Gladiator","300","Titus","Gladiator","");
        final Question qst7 = new Question("Jennifer Lawrence won a Best Actress Academy Award for what 2012 romantic comedy/drama?","The hunger games","The X-men","Silver Linings Playbook","Passangers","Silver Linings Playbook","");
        final Question qst8 = new Question("What was Quentin Tarantino‘s first feature as writer/director?","Speed","Pulp Fiction","Men in Black","Reservoir Dogs","Reservoir Dogs","");
        final Question qst9 = new Question("Who is the only person ever to receive an Oscar nomination for acting in a Star Wars movie?","Mark Hamill","Natalie Portman","Alec Guinness","Harrison Ford","Alec Guinness","");
        final Question qst10 = new Question("In which fictitious country is Black Panther set?","Middle-Earth","Wakanda","Narnia","Westeros","Wakanda","");
        final Question qst11 = new Question("Who was the first black person to win an Oscar for acting?","Hattie McDonald","Halle Berry","Morgan Freeman","Samuel L. Jackson","Hattie McDonald","");
        final Question qst12 = new Question("Who is the composer of the music in the film score of Jurassic Park?","Ennio Morricone","John Williams","John Williams","Hans Zimmer","John Williams","");
        final Question qst13 = new Question("How many James Bond films did Roger Moore star in?","7","6","5","4","7","");

        final ArrayList<Question> questionList = new ArrayList<>();

        questionList.add(qst1);
        questionList.add(qst2);
        questionList.add(qst3);
        questionList.add(qst4);
        questionList.add(qst5);
        questionList.add(qst6);
        questionList.add(qst7);
        questionList.add(qst8);
        questionList.add(qst9);
        questionList.add(qst10);
        questionList.add(qst11);
        questionList.add(qst12);
        questionList.add(qst13);


        return questionList;
    }

    private static ArrayList<Question> sportsQuestions(){

        final Question qst1 = new Question("How long is an Olympic sized swimming pool?","25m","50m","100m","200m","50m","");
        final Question qst2 = new Question("The Olympics are held every how many years?","2","3","4","5","4","");
        final Question qst3 = new Question("What sport is dubbed the ‘king of sports’?","Football","Basketball","Volleyball","Baseball","Football","");
        final Question qst4 = new Question("What is Canada’s national sport?","Hokey","Lacrosse","Basketball","Curling","Lacrosse","");
        final Question qst5 = new Question("What African country was the first ever to qualify for a World Cup?","Nigeria","Egypt","Morocco","South Africa","Egypt","");
        final Question qst6 = new Question("How many medals did China win at the Beijing Olympics in 2008?","127","100","74","52","100","");
        final Question qst7 = new Question("What is the only country to have played in every single soccer World Cup?","Argentina","Spain","Brazil","Germany","Brazil","");
        final Question qst8 = new Question("The first ever soccer World Cup was won by what country?","Argintina","Uruguay","Spain","Brazil","Uruguay","");
        final Question qst9 = new Question("What sport were women allowed to play in the Olympics for the first time?","Volleyball","Football","Swimming","Tennins","Tennis","");
        final Question qst10 = new Question("In which sport was Muhammad Ali popular?","Basketball","Boxing","Baseball","Horse Riding","Boxing","");
        final Question qst11 = new Question("Who was the first player to win the Champions League with three different clubs?","David Beckam","Clarence Seedorf","Lionel Messi","Wayne Rooney","Clarence Seedorf","");
        final Question qst12 = new Question("Who were the first team to win the European Cup, in 1956?","Barcelona","Real Madrid","Milan","Liverpool","Real Madrid","");
        final Question qst13 = new Question("Who were the first Italian team to win the European Cup?","AC Milan","Roma","Juventus","Inter","AC Milan","");
        final Question qst14 = new Question("Which was the first English team to win the European Cup/Champions League twice?","Manchester City","Manchester United","Liverpool","Arsenal","Liverpool","");
        final Question qst15 = new Question("As of 2020, which country had the greatest number of different winners?","England","Spain","Italy","France","England","");

        final ArrayList<Question> questionList = new ArrayList<>();

        questionList.add(qst1);
        questionList.add(qst2);
        questionList.add(qst3);
        questionList.add(qst4);
        questionList.add(qst5);
        questionList.add(qst6);
        questionList.add(qst7);
        questionList.add(qst8);
        questionList.add(qst9);
        questionList.add(qst10);
        questionList.add(qst11);
        questionList.add(qst12);
        questionList.add(qst13);
        questionList.add(qst14);
        questionList.add(qst15);

        return questionList;
    }

    private static ArrayList<Question> historyQuestions(){

        final Question qst1 = new Question("How many years did the 100 years war last?","105","100","116","95","116","");
        final Question qst2 = new Question("In which year was J.F.K assassinated?","1963","1964","1965","1966","1963","");
        final Question qst3 = new Question("Which is not one of the seven wonders of the ancient world?","The hanging gardens of Babylon","The pyramids of Giza","The lighthouse of Rhodes","The colloseum of Rome","The colloseum of Rome","");
        final Question qst4 = new Question("In which year did the man step on the moon?","1967","1968","1969","1970","1969","");
        final Question qst5 = new Question("Where were the first modern Olympics held?","Paris","Athens","London","Rome","Athens","");
        final Question qst6 = new Question("Who said 'Eureka'?","Platon","Aristoteles","Archimedes","Pythagoras","Archimedes","");
        final Question qst7 = new Question("In which century did the French revolution take place in?","16th","17th","18th","19th","18th","");
        final Question qst8 = new Question("In which year was the Berlin wall torn down?","1989","1988","1987","1986","1989","");
        final Question qst9 = new Question("In which country was the Incan Empire located?","Mexico","Peru","Brazil","Chile","Peru","");
        final Question qst10 = new Question("Where was Adolf Hitler born","Germany","Poland","Austria","Holland","Austria","");
        final Question qst11 = new Question("How many colonies originally declared independence in 1776?","26","13","17","9","13","");
        final Question qst12 = new Question("Who was the fourth President of the U.S.A. from 1809-1817?","James Madison","Thomas Jefferson","James Monroe","John Quincy Adams","James Madison","");
        final Question qst13 = new Question("Which state was acquired from France for $15,000,000 in 1803?","Virginia","Tennessee","Louisiana","Indiana","Louisiana","");
        final Question qst14 = new Question("Who is not a President carved into Mount Rushmore?","Washington","Jefferson","Lincoln","Adams","Adams","");

        final ArrayList<Question> questionList = new ArrayList<>();

        questionList.add(qst1);
        questionList.add(qst2);
        questionList.add(qst3);
        questionList.add(qst4);
        questionList.add(qst5);
        questionList.add(qst6);
        questionList.add(qst7);
        questionList.add(qst8);
        questionList.add(qst9);
        questionList.add(qst10);
        questionList.add(qst11);
        questionList.add(qst12);
        questionList.add(qst13);
        questionList.add(qst14);


        return questionList;
    }

    private static ArrayList<Question> scienceQuestions(){

        final Question qst1 = new Question("What is the chemical type of water?","H20","HO2","HO","HO3","H2O","");
        final Question qst2 = new Question("How many colors are there in a rainbow?","5","6","7","8","7","");
        final Question qst3 = new Question("What charge do the electrons have?","positive","no charge","neutral","negative","negative","");
        final Question qst4 = new Question("What is the most common gas in the air?","oxygen","hydrogen","nitrogen","carbon dioxide","nitrogen","");
        final Question qst5 = new Question("What is the nearest star to our solar system?","Proxima Centauri","Wolf","Sirius","Alpha Centauri","Proxima Centauri","");
        final Question qst6 = new Question("How many teeth does an adult human have?","30","32","34","36","32","");
        final Question qst7 = new Question("What is the largest planet in the solar system?","Mars","Earth","Uranus","Jupiter","Jupiter","");
        final Question qst8 = new Question("Where is the smallest part of the body located?","In the ear","In the hand","In the leg","In the nose","In the ear","");
        final Question qst9 = new Question("In what country can you find the Suez Canal?","Egypt","Greece","Morroco","Spain","Egypt","");
        final Question qst10 = new Question("How many hearts does an octopus have?","0","1","2","3","3","");
        final Question qst11 = new Question("Which galaxy is Earth in?","Milky Way","Andromeda","Triangulum Galaxy","Virgo A","Milky Way","");
        final Question qst12 = new Question("Anaemia is caused by the deficiency of which mineral in the human diet?","Vitamin C","Phosphorus","Iron","Calcium","Iron","");
        final Question qst13 = new Question("Which is the lightest noble gas?","krypton","helium","argon","neon","helium","");
        final Question qst14 = new Question("What is the largest land animal?","Elephant","Blue Whale","Lion","Giraffe","Elephant","");

        final ArrayList<Question> questionList = new ArrayList<>();

        questionList.add(qst1);
        questionList.add(qst2);
        questionList.add(qst3);
        questionList.add(qst4);
        questionList.add(qst5);
        questionList.add(qst6);
        questionList.add(qst7);
        questionList.add(qst8);
        questionList.add(qst9);
        questionList.add(qst10);
        questionList.add(qst11);
        questionList.add(qst12);
        questionList.add(qst13);
        questionList.add(qst14);


        return questionList;
    }

    private static ArrayList<Question> artQuestions(){

        final Question qst1 = new Question("Who painted the Mona Lisa?","Leonardo da Vinci","Vincent van Gogh","Pablo Picasso","Claude Monet","Leonardo da Vinci","");
        final Question qst2 = new Question("What artistic style is Pablo Picasso known for?","Surrealism","Cubism","Impressionism","Pointillism","Cubism","");
        final Question qst3 = new Question("Which painter was also a sculptor, architect and engineer?","Vincent van Gogh","Leonardo da Vinci","Pablo Picasso","Claude Monet","Leonardo da Vinci","");
        final Question qst4 = new Question("In which city is the Louvre located?","London","Paris","Amsterdam","Berlin","Paris","");
        final Question qst5 = new Question("Who wrote the Iliad?","Euripides","Beowulf","Homer","Virgil","Homer","");
        final Question qst6 = new Question("Which book is written by Jane Austin?","Wuthering Heights","Little Women","Pride and Prejudice","Anna Karenina","Pride and Prejudice","");
        final Question qst7 = new Question("Which are the names of the main characters in the Wuthering Heights?","Catherine and Heathcliff","Eleanor and Christian","Isabella and Hindley","Anne and Gordon","Catherine and Heathcliff","");
        final Question qst8 = new Question("What is the seventh book of Harry Potter?","The order of the phoenix","The half blood prince","The deathly hallows","The goblet of fire","The deathly hallows","");
        final Question qst9 = new Question("Which is the first novel of Sherlock Holmes?","The sign of the four","A study in scarlet","The valley of fear","The hound of the Baskervilles","A study in scarlet","");
        final Question qst10 = new Question("Who created the hero Hercule Poirot?","Sir Arthur Conan Doyle","Jane Austin","Stephen King","Agatha Christie","Agatha Christie","");
        final Question qst11 = new Question("Which town in Italy was Leonardo Da Vinci born?","Vinci","Florence","Rome","Milan","Vinci","");
        final Question qst12 = new Question("Which Spanish painter is referred to as both the last of the old masters and the moderns?","El Greco","Salvador Dali","Pablo Picasso","Francisco Goya","Francisco Goya","");
        final Question qst13 = new Question("Which of the following painters is not a teenage mutannt ninja turtle?","Leonardo","Rafaello","Pablo","Michaelangello","Pablo","");
        final Question qst14 = new Question("Which of the following is not a primary color?","Red","Green","Yellow","Blue","Green","");

        final ArrayList<Question> questionList = new ArrayList<>();

        questionList.add(qst1);
        questionList.add(qst2);
        questionList.add(qst3);
        questionList.add(qst4);
        questionList.add(qst5);
        questionList.add(qst6);
        questionList.add(qst7);
        questionList.add(qst8);
        questionList.add(qst9);
        questionList.add(qst10);
        questionList.add(qst11);
        questionList.add(qst12);
        questionList.add(qst13);
        questionList.add(qst14);


        return questionList;
    }

    private static ArrayList<Question> anyCategory(){

        final Question qst1 = new Question("Which is the capital of Egypt;","Cairo","Alexandria","Luxor","Giza","Cairo","");
        final Question qst2 = new Question("Which was the first capital of Greece;","Patras","Athens","Nafplio","Thessaloniki","Nafplio","");
        final Question qst3 = new Question("Which country belongs in the Balkans;","Spain","Turkey","Bulgaria","Holland","Bulgaria","");
        final Question qst4 = new Question("Which country is not a neighbor of Greece;","Bulgaria","Roumania","Turkey","Albania","Roumania","");
        final Question qst5 = new Question("How many stars does the EU flag has;","14","13","12","11","12","");
        final Question qst6 = new Question("How many states does the USA have;","50","51","52","54","50","");
        final Question qst7 = new Question("Which is the capital of the USA;","Seattle","Washington","New York","California","Washington","");
        final Question qst8 = new Question("Which is the capital of Canada;","Toronto","Nevada","Ottawa","Ontario","Ottawa","");
        final Question qst9 = new Question("Which is the national animal of Scotland;","Fox","Unicorn","Horse","Dolphin","Unicorn","");
        final Question qst10 = new Question("How many countries are there in the UK;","3","2","1","4","4","");
        final Question qst11 = new Question("On which continent would you find Lake Vostock?","America","Asia","Antarctica","Europe","Antarctica","");
        final Question qst12 = new Question("Out of these four capital cities, which is the most northerly?","Moscow","Tokyo","Ottawa","Helsinki","Helsinki","");
        final Question qst13 = new Question("Where in the world would you find the largest volcano?","Hawaii","Russia","USA","Nepal","Hawaii","");
        final Question qst14 = new Question("In terms of area, which is the world’s largest landlocked country?","Kazakhstan","Mongolia","Egypt","Paraguay","Kazakhstan","");
        final Question qst15 = new Question("What was the first feature-length animated movie ever released?","The little mermaid","Snow White and the Seven Dwarfs","Cinderella","The sleeping beauty","Snow White and the Seven Dwarfs","");
        final Question qst16 = new Question("In The Matrix, does Neo take the blue pill or the red pill?","Red","Blue","Both","Neither","Red","");
        final Question qst17 = new Question("In what 1976 thriller does Robert De Niro famously say “You talkin’ to me?”","The godfather","Die Hard","Mission Impossible","Taxi Driver","Taxi Driver","");
        final Question qst18 = new Question("What is the highest-grossing R-rated movie of all time?","Joker","Guardians of the Galaxy","Pulp Fiction","Deadpool","Joker","");
        final Question qst19 = new Question("In what 1979 James Bond movie does the famous spy go to outer space?","Moonraker","From Russia with love","Goldfinger","The spy I loved","Moonraker","");
        final Question qst20 = new Question("Joaquin Phoenix received his first Oscar nomination for playing Roman emperor Commodus in what 2000 Oscar-winning epic?","Pompei","Gladiator","300","Titus","Gladiator","");
        final Question qst21 = new Question("Jennifer Lawrence won a Best Actress Academy Award for what 2012 romantic comedy/drama?","The hunger games","The X-men","Silver Linings Playbook","Passangers","Silver Linings Playbook","");
        final Question qst22 = new Question("What was Quentin Tarantino‘s first feature as writer/director?","Speed","Pulp Fiction","Men in Black","Reservoir Dogs","Reservoir Dogs","");
        final Question qst23 = new Question("Who is the only person ever to receive an Oscar nomination for acting in a Star Wars movie?","Mark Hamill","Natalie Portman","Alec Guinness","Harrison Ford","Alec Guinness","");
        final Question qst24 = new Question("In which fictitious country is Black Panther set?","Middle-Earth","Wakanda","Narnia","Westeros","Wakanda","");
        final Question qst25 = new Question("Who was the first black person to win an Oscar for acting?","Hattie McDonald","Halle Berry","Morgan Freeman","Samuel L. Jackson","Hattie McDonald","");
        final Question qst26 = new Question("Who is the composer of the music in the film score of Jurassic Park?","Ennio Morricone","John Williams","John Williams","Hans Zimmer","John Williams","");
        final Question qst27 = new Question("How many James Bond films did Roger Moore star in?","7","6","5","4","7","");
        final Question qst28 = new Question("How long is an Olympic sized swimming pool?","25m","50m","100m","200m","50m","");
        final Question qst29 = new Question("The Olympics are held every how many years?","2","3","4","5","4","");
        final Question qst30 = new Question("What sport is dubbed the ‘king of sports’?","Football","Basketball","Volleyball","Baseball","Football","");
        final Question qst31 = new Question("What is Canada’s national sport?","Hokey","Lacrosse","Basketball","Curling","Lacrosse","");
        final Question qst32 = new Question("What African country was the first ever to qualify for a World Cup?","Nigeria","Egypt","Morocco","South Africa","Egypt","");
        final Question qst33 = new Question("How many medals did China win at the Beijing Olympics in 2008?","127","100","74","52","100","");
        final Question qst34 = new Question("What is the only country to have played in every single soccer World Cup?","Argentina","Spain","Brazil","Germany","Brazil","");
        final Question qst35 = new Question("The first ever soccer World Cup was won by what country?","Argintina","Uruguay","Spain","Brazil","Uruguay","");
        final Question qst36 = new Question("What sport were women allowed to play in the Olympics for the first time?","Volleyball","Football","Swimming","Tennins","Tennis","");
        final Question qst37 = new Question("In which sport was Muhammad Ali popular?","Basketball","Boxing","Baseball","Horse Riding","Boxing","");
        final Question qst38 = new Question("Who was the first player to win the Champions League with three different clubs?","David Beckam","Clarence Seedorf","Lionel Messi","Wayne Rooney","Clarence Seedorf","");
        final Question qst39 = new Question("Who were the first team to win the European Cup, in 1956?","Barcelona","Real Madrid","Milan","Liverpool","Real Madrid","");
        final Question qst40 = new Question("Who were the first Italian team to win the European Cup?","AC Milan","Roma","Juventus","Inter","AC Milan","");
        final Question qst41 = new Question("Which was the first English team to win the European Cup/Champions League twice?","Manchester City","Manchester United","Liverpool","Arsenal","Liverpool","");
        final Question qst42 = new Question("As of 2020, which country had the greatest number of different winners?","England","Spain","Italy","France","England","");
        final Question qst43 = new Question("How many years did the 100 years war last?","105","100","116","95","116","");
        final Question qst44 = new Question("In which year was J.F.K assassinated?","1963","1964","1965","1966","1963","");
        final Question qst45 = new Question("Which is not one of the seven wonders of the ancient world?","The hanging gardens of Babylon","The pyramids of Giza","The lighthouse of Rhodes","The colloseum of Rome","The colloseum of Rome","");
        final Question qst46 = new Question("In which year did the man step on the moon?","1967","1968","1969","1970","1969","");
        final Question qst47 = new Question("Where were the first modern Olympics held?","Paris","Athens","London","Rome","Athens","");
        final Question qst48 = new Question("Who said 'Eureka'?","Platon","Aristoteles","Archimedes","Pythagoras","Archimedes","");
        final Question qst49 = new Question("In which century did the French revolution take place in?","16th","17th","18th","19th","18th","");
        final Question qst50 = new Question("In which year was the Berlin wall torn down?","1989","1988","1987","1986","1989","");
        final Question qst51 = new Question("In which country was the Incan Empire located?","Mexico","Peru","Brazil","Chile","Peru","");
        final Question qst52 = new Question("Where was Adolf Hitler born","Germany","Poland","Austria","Holland","Austria","");
        final Question qst53 = new Question("How many colonies originally declared independence in 1776?","26","13","17","9","13","");
        final Question qst54 = new Question("Who was the fourth President of the U.S.A. from 1809-1817?","James Madison","Thomas Jefferson","James Monroe","John Quincy Adams","James Madison","");
        final Question qst55 = new Question("Which state was acquired from France for $15,000,000 in 1803?","Virginia","Tennessee","Louisiana","Indiana","Louisiana","");
        final Question qst56 = new Question("Who is not a President carved into Mount Rushmore?","Washington","Jefferson","Lincoln","Adams","Adams","");
        final Question qst57 = new Question("What is the chemical type of water?","H20","HO2","HO","HO3","H2O","");
        final Question qst58 = new Question("How many colors are there in a rainbow?","5","6","7","8","7","");
        final Question qst59 = new Question("What charge do the electrons have?","positive","no charge","neutral","negative","negative","");
        final Question qst60 = new Question("What is the most common gas in the air?","oxygen","hydrogen","nitrogen","carbon dioxide","nitrogen","");
        final Question qst61 = new Question("What is the nearest star to our solar system?","Proxima Centauri","Wolf","Sirius","Alpha Centauri","Proxima Centauri","");
        final Question qst62 = new Question("How many teeth does an adult human have?","30","32","34","36","32","");
        final Question qst63 = new Question("What is the largest planet in the solar system?","Mars","Earth","Uranus","Jupiter","Jupiter","");
        final Question qst64 = new Question("Where is the smallest part of the body located?","In the ear","In the hand","In the leg","In the nose","In the ear","");
        final Question qst65 = new Question("In what country can you find the Suez Canal?","Egypt","Greece","Morroco","Spain","Egypt","");
        final Question qst66 = new Question("How many hearts does an octopus have?","0","1","2","3","3","");
        final Question qst67 = new Question("Which galaxy is Earth in?","Milky Way","Andromeda","Triangulum Galaxy","Virgo A","Milky Way","");
        final Question qst68 = new Question("Anaemia is caused by the deficiency of which mineral in the human diet?","Vitamin C","Phosphorus","Iron","Calcium","Iron","");
        final Question qst69 = new Question("Which is the lightest noble gas?","krypton","helium","argon","neon","helium","");
        final Question qst70 = new Question("What is the largest land animal?","Elephant","Blue Whale","Lion","Giraffe","Elephant","");
        final Question qst71 = new Question("Who painted the Mona Lisa?","Leonardo da Vinci","Vincent van Gogh","Pablo Picasso","Claude Monet","Leonardo da Vinci","");
        final Question qst72 = new Question("What artistic style is Pablo Picasso known for?","Surrealism","Cubism","Impressionism","Pointillism","Cubism","");
        final Question qst73 = new Question("Which painter was also a sculptor, architect and engineer?","Vincent van Gogh","Leonardo da Vinci","Pablo Picasso","Claude Monet","Leonardo da Vinci","");
        final Question qst74 = new Question("In which city is the Louvre located?","London","Paris","Amsterdam","Berlin","Paris","");
        final Question qst75 = new Question("Who wrote the Iliad?","Euripides","Beowulf","Homer","Virgil","Homer","");
        final Question qst76 = new Question("Which book is written by Jane Austin?","Wuthering Heights","Little Women","Pride and Prejudice","Anna Karenina","Pride and Prejudice","");
        final Question qst77 = new Question("Which are the names of the main characters in the Wuthering Heights?","Catherine and Heathcliff","Eleanor and Christian","Isabella and Hindley","Anne and Gordon","Catherine and Heathcliff","");
        final Question qst78 = new Question("What is the seventh book of Harry Potter?","The order of the phoenix","The half blood prince","The deathly hallows","The goblet of fire","The deathly hallows","");
        final Question qst79 = new Question("Which is the first novel of Sherlock Holmes?","The sign of the four","A study in scarlet","The valley of fear","The hound of the Baskervilles","A study in scarlet","");
        final Question qst80 = new Question("Who created the hero Hercule Poirot?","Sir Arthur Conan Doyle","Jane Austin","Stephen King","Agatha Christie","Agatha Christie","");
        final Question qst81 = new Question("Which town in Italy was Leonardo Da Vinci born?","Vinci","Florence","Rome","Milan","Vinci","");
        final Question qst82 = new Question("Which Spanish painter is referred to as both the last of the old masters and the moderns?","El Greco","Salvador Dali","Pablo Picasso","Francisco Goya","Francisco Goya","");
        final Question qst83 = new Question("Which of the following painters is not a teenage mutannt ninja turtle?","Leonardo","Rafaello","Pablo","Michaelangello","Pablo","");
        final Question qst84 = new Question("Which of the following is not a primary color?","Red","Green","Yellow","Blue","Green","");

        final ArrayList<Question> questionList = new ArrayList<>();

        questionList.add(qst1);
        questionList.add(qst2);
        questionList.add(qst3);
        questionList.add(qst4);
        questionList.add(qst5);
        questionList.add(qst6);
        questionList.add(qst7);
        questionList.add(qst8);
        questionList.add(qst9);
        questionList.add(qst10);
        questionList.add(qst11);
        questionList.add(qst12);
        questionList.add(qst13);
        questionList.add(qst14);
        questionList.add(qst15);
        questionList.add(qst16);
        questionList.add(qst17);
        questionList.add(qst18);
        questionList.add(qst19);
        questionList.add(qst20);
        questionList.add(qst21);
        questionList.add(qst22);
        questionList.add(qst23);
        questionList.add(qst24);
        questionList.add(qst25);
        questionList.add(qst26);
        questionList.add(qst27);
        questionList.add(qst28);
        questionList.add(qst29);
        questionList.add(qst30);
        questionList.add(qst31);
        questionList.add(qst32);
        questionList.add(qst33);
        questionList.add(qst34);
        questionList.add(qst35);
        questionList.add(qst36);
        questionList.add(qst37);
        questionList.add(qst38);
        questionList.add(qst39);
        questionList.add(qst40);
        questionList.add(qst41);
        questionList.add(qst42);
        questionList.add(qst43);
        questionList.add(qst44);
        questionList.add(qst45);
        questionList.add(qst46);
        questionList.add(qst47);
        questionList.add(qst48);
        questionList.add(qst49);
        questionList.add(qst50);
        questionList.add(qst51);
        questionList.add(qst52);
        questionList.add(qst53);
        questionList.add(qst54);
        questionList.add(qst55);
        questionList.add(qst56);
        questionList.add(qst57);
        questionList.add(qst58);
        questionList.add(qst59);
        questionList.add(qst60);
        questionList.add(qst61);
        questionList.add(qst62);
        questionList.add(qst63);
        questionList.add(qst64);
        questionList.add(qst65);
        questionList.add(qst66);
        questionList.add(qst67);
        questionList.add(qst68);
        questionList.add(qst69);
        questionList.add(qst70);
        questionList.add(qst71);
        questionList.add(qst72);
        questionList.add(qst73);
        questionList.add(qst74);
        questionList.add(qst75);
        questionList.add(qst76);
        questionList.add(qst77);
        questionList.add(qst78);
        questionList.add(qst79);
        questionList.add(qst80);
        questionList.add(qst81);
        questionList.add(qst82);
        questionList.add(qst83);
        questionList.add(qst84);


        return questionList;
    }

    public static ArrayList<Question> getQuestions(String selectedCategory){
        switch (selectedCategory){
            case "Geography":
                return geographyQuestions();

            case "Entertainment":
                return entertainmentQuestions();

            case "Sports":
                return sportsQuestions();

            case "History":
                return historyQuestions();

            case "Science":
                return scienceQuestions();

            case "Art":
                return artQuestions();

            default:
                return anyCategory();
        }
    }
    public void uploadQuestionsToDB(){
        DatabaseReference dbreference  = FirebaseDatabase.getInstance("https://quizmegame-df195-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Questions");

        dbreference.child("Geography").setValue(geographyQuestions());
        dbreference.child("Entertainment").setValue(entertainmentQuestions());
        dbreference.child("Sports").setValue(sportsQuestions());
        dbreference.child("History").setValue(historyQuestions());
        dbreference.child("Science").setValue(scienceQuestions());
        dbreference.child("Art").setValue(artQuestions());
        dbreference.child("Any Category").setValue(anyCategory());

    }

}
