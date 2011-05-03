import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.apache.derby.jdbc.ClientDriver;

public class VideoGame
{
  public static void main(String[] paramArrayOfString)
  {
    Scanner localScanner = new Scanner(System.in);
    System.out.println("\nWelcome to the Video Game Database!\n");
    System.out.println("Are you an Administrator or a User?\n");
    System.out.print("1 - Administrator\n2 - User\n\nPlease enter a selection: ");
    int i = localScanner.nextInt();

    if (i == 1)
    {
      adminMenu();
    }
    else if (i == 2)
    {
      userMenu();
    }
    else
    {
      System.out.println("\nInvalid entry, try again.\n");
    }
  }

  public static void adminMenu()
  {
    Scanner localScanner = new Scanner(System.in);
    int i = 1;

    while (i != 0)
    {
      System.out.println("\nAdministrator Menu");
      System.out.println("------------------\n");
      System.out.println("1 - View Games\t\t6 - View Platforms\t11 - ReleaseDateSearch\t\t16 - Insert Game");
      System.out.println("2 - View Developers\t7 - View Characters\t12 - Review Rating Search\t17 - Insert Developer");
      System.out.println("3 - View Publishers\t8 - Select Platform\t13 - Game Rating Search\t\t18 - Insert Publisher");
      System.out.println("4 - View Reviews\t9 - Game Reviews\t14 - Character Search");
      System.out.println("5 - View Reviewers\t10 - Games by Platform\t15 - Genre Search");
      System.out.println("\n0 - Exit");
      System.out.print("\nPlease select an option: ");
      i = localScanner.nextInt();
      System.out.println();

      switch (i) { case 0:
        System.out.println("Thanks for using the Video Game Database!"); break;
      case 1:
        viewGames(); break;
      case 2:
        viewDevelopers(); break;
      case 3:
        viewPublishers(); break;
      case 4:
        viewReviews(); break;
      case 5:
        viewReviewers(); break;
      case 6:
        viewPlatforms(); break;
      case 7:
        viewCharacters(); break;
      case 8:
        selectPlatform(); break;
      case 9:
        gameReviews(); break;
      case 10:
        gamePlatform(); break;
      case 11:
        dateRange(); break;
      case 12:
        reviewRange(); break;
      case 13:
        ratingSearch(); break;
      case 14:
        searchCharacters(); break;
      case 15:
        genreSearch(); break;
      case 16:
        insertGame(); break;
      case 17:
        insertDeveloper(); break;
      case 18:
        insertPublisher(); break;
      default:
        System.out.println("Invalid option, please try again.\n");
      }
    }
  }

  public static void userMenu()
  {
    Scanner localScanner = new Scanner(System.in);
    int i = 1;

    while (i != 0)
    {
      System.out.println("\nUser Menu");
      System.out.println("---------\n");
      System.out.println("1 - View Games\t\t6 - View Platforms\t11 - ReleaseDateSearch");
      System.out.println("2 - View Developers\t7 - View Characters\t12 - Review Rating Search");
      System.out.println("3 - View Publishers\t8 - Select Platform\t13 - Game Rating Search");
      System.out.println("4 - View Reviews\t9 - Game Reviews\t14 - Character Search");
      System.out.println("5 - View Reviewers\t10 - Games by Platform\t15 - Genre Search");
      System.out.println("\n0 - Exit");
      System.out.print("\nPlease select an option: ");
      i = localScanner.nextInt();
      System.out.println();

      switch (i) { case 0:
        System.out.println("Thanks for using the Video Game Database!"); break;
      case 1:
        viewGames(); break;
      case 2:
        viewDevelopers(); break;
      case 3:
        viewPublishers(); break;
      case 4:
        viewReviews(); break;
      case 5:
        viewReviewers(); break;
      case 6:
        viewPlatforms(); break;
      case 7:
        viewCharacters(); break;
      case 8:
        selectPlatform(); break;
      case 9:
        gameReviews(); break;
      case 10:
        gamePlatform(); break;
      case 11:
        dateRange(); break;
      case 12:
        reviewRange(); break;
      case 13:
        ratingSearch(); break;
      case 14:
        searchCharacters(); break;
      case 15:
        genreSearch(); break;
      default:
        System.out.println("Invalid option, please try again.\n"); }
    }
  }

  public static void viewGames() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select GName, PubName, DevName, GenreName, Rating, ReleaseDate from GAME, DEVELOPER, PUBLISHER, GENRE where PublisherId = PubId and DeveloperId = DevId and GenreId = GenreType order by GId";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("Title\t\t\tPublisher\tDeveloper\tGenre\tRating\t\tYear");
      System.out.println("-----\t\t\t---------\t---------\t-----\t------\t\t----");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("GName");
        String str4 = localResultSet.getString("PubName");
        String str5 = localResultSet.getString("DevName");
        String str6 = localResultSet.getString("GenreName");
        String str7 = localResultSet.getString("Rating");
        int i = localResultSet.getInt("ReleaseDate");
        System.out.println(str3 + "\t\t" + str4 + "\t" + str5 + "\t" + str6 + "\t" + str7 + "\t\t" + i);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void viewDevelopers() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select DevName from DEVELOPER order by DevName";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("Developers");
      System.out.println("----------");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("DevName");
        System.out.println(str3);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void viewPublishers() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select PubName from PUBLISHER order by PubName";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("Publishers");
      System.out.println("----------");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("PubName");
        System.out.println(str3);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void viewReviews() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select GName, ReviewerName, ReviewRating from GAME, REVIEW, REVIEWER where GId = GameRevId and ReviewerNum = ReviewerId order by GId";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("Title\t\tReviewer\t\tRating");
      System.out.println("-----\t\t--------\t\t------");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("GName");
        String str4 = localResultSet.getString("ReviewerName");
        int i = localResultSet.getInt("ReviewRating");
        System.out.println(str3 + "\t\t" + str4 + "\t\t" + i);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void viewReviewers() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select ReviewerName from REVIEWER order by ReviewerName";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("Reviewers");
      System.out.println("---------");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("ReviewerName");
        System.out.println(str3);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void viewPlatforms() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select PlatName from PLATFORM order by PlatName";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("Platforms");
      System.out.println("---------");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("PlatName");
        System.out.println(str3);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void viewCharacters() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select CharName from MAIN_CHARACTERS order by CharName";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("Main Characters");
      System.out.println("---------------");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("CharName");
        System.out.println(str3);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void selectPlatform() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select PlatId, PlatName from PLATFORM order by PlatId";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("ID\tPlatform");
      System.out.println("--\t--------");
      while (localResultSet.next()) {
        int i = localResultSet.getInt("PlatId");
        String str3 = localResultSet.getString("PlatName");
        System.out.println(i + "\t" + str3);
      }
      System.out.print("\n");
      localResultSet.close();

      Scanner localScanner = new Scanner(System.in);
      System.out.print("Select an ID to view that platform's games: ");
      int j = localScanner.nextInt();

      str2 = "select GName from GAME, PLATFORM_SET, PLATFORM where GId = GamePlatId and PlatformId =" + j + " group by GName order by GName";
      localResultSet = localStatement.executeQuery(str2);

      System.out.println("\nSelected Titles");
      System.out.println("---------------");
      while (localResultSet.next()) {
        String str4 = localResultSet.getString("GName");
        System.out.println(str4);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void gameReviews() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select GId, GName from GAME order by GId";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("ID\tTitle");
      System.out.println("--\t-----");
      while (localResultSet.next()) {
        int i = localResultSet.getInt("GId");
        String str3 = localResultSet.getString("GName");
        System.out.println(i + "\t" + str3);
      }
      System.out.print("\n");
      localResultSet.close();

      System.out.print("Please select a game to view its reviews: ");
      Scanner localScanner = new Scanner(System.in);
      int j = localScanner.nextInt();

      str2 = "select ReviewerName, ReviewRating from GAME, REVIEW, REVIEWER where GId = GameRevId and ReviewerNum = ReviewerId and GId = " + j + " order by ReviewerName";
      localResultSet = localStatement.executeQuery(str2);

      System.out.println("\nReviewer\tRating");
      System.out.println("--------\t------");
      while (localResultSet.next()) {
        String str4 = localResultSet.getString("ReviewerName");
        int k = localResultSet.getInt("ReviewRating");
        System.out.println(str4 + "\t" + k);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void gamePlatform() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select GName, PlatName from GAME, PLATFORM_SET, PLATFORM where GId = GamePlatId and PlatformId = PlatId order by PlatName";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("Platform\tTitle");
      System.out.println("--------\t-----");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("GName");
        String str4 = localResultSet.getString("PlatName");
        System.out.println(str4 + "\t" + str3);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void dateRange() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Scanner localScanner = new Scanner(System.in);
      System.out.print("Please enter a beginning year: ");
      int i = localScanner.nextInt();
      System.out.print("Please enter an ending year: ");
      int j = localScanner.nextInt();

      Statement localStatement = localConnection.createStatement();
      String str2 = "select GName, ReleaseDate from GAME where ReleaseDate >= " + i + " and ReleaseDate <= " + j + " order by GId";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("\nTitles in Date Range\tYear");
      System.out.println("--------------------\t----");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("GName");
        int k = localResultSet.getInt("ReleaseDate");
        System.out.println(str3 + "\t" + k);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void reviewRange() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Scanner localScanner = new Scanner(System.in);
      System.out.print("Please enter a minimum review rating: ");
      int i = localScanner.nextInt();
      System.out.print("Please enter a maximum review rating: ");
      int j = localScanner.nextInt();

      Statement localStatement = localConnection.createStatement();
      String str2 = "select GName, ReviewerName, ReviewRating from GAME, REVIEW, REVIEWER where GId = GameRevId and ReviewerNum = ReviewerId and ReviewRating >= " + i + " and ReviewRating <= " + j + " order by GName";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("\nTitles in Review Range\tReviewer\tRating");
      System.out.println("----------------------\t--------\t------");
      while (localResultSet.next()) {
        String str3 = localResultSet.getString("GName");
        String str4 = localResultSet.getString("ReviewerName");
        int k = localResultSet.getInt("ReviewRating");
        System.out.println(str3 + "\t" + str4 + "\t" + k);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void ratingSearch() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Scanner localScanner = new Scanner(System.in);
      System.out.print("Please enter an ESRB rating: ");
      String str2 = localScanner.nextLine();

      Statement localStatement = localConnection.createStatement();
      String str3 = "select GName, Rating, ReleaseDate from GAME where Rating = '" + str2 + "' order by GName";
      ResultSet localResultSet = localStatement.executeQuery(str3);

      System.out.println("\nTitles with ESRB Rating\tRating\tReleaseDate");
      System.out.println("-----------------------\t------\t-----------");
      while (localResultSet.next()) {
        String str4 = localResultSet.getString("GName");
        String str5 = localResultSet.getString("Rating");
        int i = localResultSet.getInt("ReleaseDate");
        System.out.println(str4 + "\t" + str5 + "\t" + i);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void searchCharacters() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select CharId, CharName from MAIN_CHARACTERS order by CharId";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("ID\tCharacter");
      System.out.println("--\t---------");
      while (localResultSet.next()) {
        int i = localResultSet.getInt("CharId");
        String str3 = localResultSet.getString("CharName");
        System.out.println(i + "\t" + str3);
      }
      System.out.print("\n");
      localResultSet.close();

      System.out.print("Please select a character to view game appearances: ");
      Scanner localScanner = new Scanner(System.in);
      int j = localScanner.nextInt();

      str2 = "select GName, ReleaseDate from GAME, SET_CHARACTER, MAIN_CHARACTERS where GId = GameCharId and CharacterId = CharId and CharId = " + j + " order by ReleaseDate";
      localResultSet = localStatement.executeQuery(str2);

      System.out.println("\nAppearances\tRelease Date");
      System.out.println("-----------\t------------");
      while (localResultSet.next()) {
        String str4 = localResultSet.getString("GName");
        int k = localResultSet.getInt("ReleaseDate");
        System.out.println(str4 + "\t" + k);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void genreSearch() {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select GenreId, GenreName from GENRE order by GenreId";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      System.out.println("ID\tGenre");
      System.out.println("--\t-----");
      while (localResultSet.next()) {
        int i = localResultSet.getInt("GenreId");
        String str3 = localResultSet.getString("GenreName");
        System.out.println(i + "\t" + str3);
      }
      System.out.print("\n");
      localResultSet.close();

      System.out.print("Please select a genre to view the corresponding games: ");
      Scanner localScanner = new Scanner(System.in);
      int j = localScanner.nextInt();

      str2 = "select GName, Rating, ReleaseDate from GAME, GENRE where GenreType = GenreId and GenreId = " + j + " order by ReleaseDate";
      localResultSet = localStatement.executeQuery(str2);

      System.out.println("\nSelected Titles\tRating\tRelease Date");
      System.out.println("---------------\t------\t------------");
      while (localResultSet.next()) {
        String str4 = localResultSet.getString("GName");
        String str5 = localResultSet.getString("Rating");
        int k = localResultSet.getInt("ReleaseDate");
        System.out.println(str4 + "\t" + str5 + "\t" + k);
      }
      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void insertGame()
  {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost:1520/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select count(GId) from GAME";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      int i = 1;
      while (localResultSet.next())
      {
        i = localResultSet.getInt("1");
      }
      i++;

      Scanner localScanner = new Scanner(System.in);
      System.out.print("Please enter the new game title: ");
      String str3 = localScanner.nextLine();
      System.out.print("\nPlease enter the developer ID: ");
      int j = localScanner.nextInt();
      System.out.print("\nPlease enter the publisher ID: ");
      int k = localScanner.nextInt();
      System.out.print("\nPlease enter the genre ID: ");
      int m = localScanner.nextInt();
      System.out.print("\nPlease enter the ESRB rating: ");
      String str4 = localScanner.nextLine();
      System.out.print("\nPlease enter the release year: ");
      int n = localScanner.nextInt();

      String str5 = "insert into GAME values (" + i + ", '" + str3 + "', " + j + ", " + k + ", " + m + ", '" + str4 + "', " + n + ")";
      localStatement.executeUpdate(str5);

      System.out.print("\n");
      localResultSet.close();
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void insertDeveloper()
  {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select count(DevId) from DEVELOPER";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      int i = 1;
      while (localResultSet.next())
      {
        i = localResultSet.getInt("1");
      }
      i++;

      Scanner localScanner = new Scanner(System.in);
      System.out.print("Please enter the new developer's name: ");
      String str3 = localScanner.nextLine();

      String str4 = "insert into DEVELOPER values (" + i + ", '" + str3 + "')";
      localStatement.executeUpdate(str4);

      System.out.print("\n");
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }

  public static void insertPublisher()
  {
    Connection localConnection = null;
    try {
      ClientDriver localClientDriver = new ClientDriver();
      String str1 = "jdbc:derby://localhost/videogamedb;create=false";
      localConnection = localClientDriver.connect(str1, null);

      Statement localStatement = localConnection.createStatement();
      String str2 = "select count(PubId) from PUBLISHER";
      ResultSet localResultSet = localStatement.executeQuery(str2);

      int i = 1;
      while (localResultSet.next())
      {
        i = localResultSet.getInt("1");
      }
      i++;

      Scanner localScanner = new Scanner(System.in);
      System.out.print("Please enter the new publisher's name: ");
      String str3 = localScanner.nextLine();

      String str4 = "insert into PUBLISHER values (" + i + ", '" + str3 + "')";
      localStatement.executeUpdate(str4);

      System.out.print("\n");
    }
    catch (SQLException localSQLException3) {
      localSQLException2.printStackTrace();
    }
    finally {
      try {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException4) {
        localSQLException4.printStackTrace();
      }
    }
  }
}