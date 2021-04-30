// Kim-Long Do
// 10/6/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #1
//
// This program will print out a version of the nursery rhyme "There Was an Old Woman"
public class Song {

    public static void main(String[] args) {
        fly2();
        spider2();
        bird2();
        cat2();
        dog2();
        bear2();
        horse();
    }
    
    //fly1 will be used to help get the line about the fly for the entire song
    // and creates a new line.
    public static void fly1() {
        System.out.println("I don't know why she swallowed that fly,");
        System.out.println("Perhaps she'll die.");
        System.out.println();
    }
    
    //fly2 creates the first verse of the song.
    public static void fly2() {
        System.out.println("There was an old woman who swallowed a fly.");
        fly1();
    }

    //spider1 will be used to help get the line about the spider for the entire song
    public static void spider1() {
        System.out.println("She swallowed the spider to catch the fly,");
        fly1();
    }
   
    //spider2 creates the second verse of the song.
    public static void spider2() {
        System.out.println("There was an old woman who swallowed a spider,");
        System.out.println("That wriggled and iggled and jiggled inside her.");
        spider1();
    }

    //bird1 will be used to help get the line about the bird for the entire song
    public static void bird1() {
        System.out.println("She swallowed the bird to catch the spider,");
        spider1();
    }
    
    //bird2 creates the third verse of the song.
    public static void bird2() {
        System.out.println("There was an old woman who swallowed a bird,");
        System.out.println("How absurd to swallow a bird.");
        bird1();
    }

    //cat1 will be used to help get the line about the cat for the entire song
    public static void cat1() {
        System.out.println("She swallowed the cat to catch the bird,");
        bird1();
    }

    //cat2 creates the fourth verse of the song.
    public static void cat2() {
        System.out.println("There was an old woman who swallowed a cat,");
        System.out.println("Imagine that to swallow a cat.");
        cat1();
    }

    //dog1 will be used to help get the line about the dog for the entire song
    public static void dog1() {
        System.out.println("She swallowed the dog to catch the cat,");
        cat1();
    }

    //dog2 creates the fifth verse of the song.
    public static void dog2() {
        System.out.println("There was an old woman who swallowed a dog,");
        System.out.println("What a hog to swallow a dog.");
        dog1();
    }

    //bear1 will be used to help get the line about the bear for the entire song
    // which is just one line but yeah.
    public static void bear1() {
        System.out.println("She swallowed the bear to catch the dog,");
        dog1();
    }

    //bear2 creates the sixth verse of the song.
    public static void bear2() {
        System.out.println("There was an old woman who swallowed a bear,");
        System.out.println("What a scare to swallow a bear.");
        bear1();
    }

    //horse creates the final verse of the song.
    public static void horse() {
        System.out.println("There was an old woman who swallowed a horse,");
        System.out.println("She died of course.");
    }
}
