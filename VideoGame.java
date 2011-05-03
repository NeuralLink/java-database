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
    Scanner instream = new Scanner(System.in);
    System.out.println("\nWelcome to the Video Game Database!\n");
    System.out.println("Are you an Administrator or a User?\n");
    System.out.print("1 - Administrator\n2 - User\n\nPlease enter a selection: ");
    int i = instream.nextInt();

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
    Scanner instream = new Scanner(System.in);
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
      i = instream.nextInt();
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
    Scanner instream = new Scanner(System.in);
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
      i = instream.nextInt();
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
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select GName, PubName, DevName, GenreName, Rating, ReleaseDate from GAME, DEVELOPER, PUBLISHER, GENRE where PublisherId = PubId and DeveloperId = DevId and GenreId = GenreType order by GId";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("Title\t\t\tPublisher\tDeveloper\tGenre\tRating\t\tYear");
      System.out.println("-----\t\t\t---------\t---------\t-----\t------\t\t----");
      while (rs.next()) {
        String gname = rs.getString("GName");
        String pubname = rs.getString("PubName");
        String devname = rs.getString("DevName");
        String genrename = rs.getString("GenreName");
        String rating = rs.getString("Rating");
        int releasedate = rs.getInt("ReleaseDate");
        System.out.println(gname + "\t\t" + pubname + "\t" + devname + "\t" + genrename + "\t" + rating + "\t\t" + releasedate);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void viewDevelopers() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select DevName from DEVELOPER order by DevName";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("Developers");
      System.out.println("----------");
      while (rs.next()) {
        String devname = rs.getString("DevName");
        System.out.println(devname);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void viewPublishers() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select PubName from PUBLISHER order by PubName";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("Publishers");
      System.out.println("----------");
      while (rs.next()) {
        String pubname = rs.getString("PubName");
        System.out.println(pubname);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void viewReviews() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select GName, ReviewerName, ReviewRating from GAME, REVIEW, REVIEWER where GId = GameRevId and ReviewerNum = ReviewerId order by GId";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("Title\t\tReviewer\t\tRating");
      System.out.println("-----\t\t--------\t\t------");
      while (rs.next()) {
        String gname = rs.getString("GName");
        String reviewername = rs.getString("ReviewerName");
        int reviewrating = rs.getInt("ReviewRating");
        System.out.println(gname + "\t\t" + reviewername + "\t\t" + reviewrating);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void viewReviewers() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select ReviewerName from REVIEWER order by ReviewerName";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("Reviewers");
      System.out.println("---------");
      while (rs.next()) {
        String reviewername = rs.getString("ReviewerName");
        System.out.println(reviewername);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void viewPlatforms() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select PlatName from PLATFORM order by PlatName";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("Platforms");
      System.out.println("---------");
      while (rs.next()) {
        String platname = rs.getString("PlatName");
        System.out.println(platname);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void viewCharacters() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select CharName from MAIN_CHARACTERS order by CharName";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("Main Characters");
      System.out.println("---------------");
      while (rs.next()) {
        String charname = rs.getString("CharName");
        System.out.println(charname);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void selectPlatform() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select PlatId, PlatName from PLATFORM order by PlatId";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("ID\tPlatform");
      System.out.println("--\t--------");
      while (rs.next()) {
        int platid = rs.getInt("PlatId");
        String platname = rs.getString("PlatName");
        System.out.println(platid + "\t" + platname);
      }
      System.out.print("\n");
      rs.close();

      Scanner instream = new Scanner(System.in);
      System.out.print("Select an ID to view that platform's games: ");
      int j = instream.nextInt();

      qry = "select GName from GAME, PLATFORM_SET, PLATFORM where GId = GamePlatId and PlatformId =" + j + " group by GName order by GName";
      rs = stmt.executeQuery(qry);

      System.out.println("\nSelected Titles");
      System.out.println("---------------");
      while (rs.next()) {
        String str4 = rs.getString("GName");
        System.out.println(str4);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void gameReviews() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select GId, GName from GAME order by GId";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("ID\tTitle");
      System.out.println("--\t-----");
      while (rs.next()) {
        int gid = rs.getInt("GId");
        String gname = rs.getString("GName");
        System.out.println(gid + "\t" + gname);
      }
      System.out.print("\n");
      rs.close();

      System.out.print("Please select a game to view its reviews: ");
      Scanner instream = new Scanner(System.in);
      int j = instream.nextInt();

      qry = "select ReviewerName, ReviewRating from GAME, REVIEW, REVIEWER where GId = GameRevId and ReviewerNum = ReviewerId and GId = " + j + " order by ReviewerName";
      rs = stmt.executeQuery(qry);

      System.out.println("\nReviewer\tRating");
      System.out.println("--------\t------");
      while (rs.next()) {
        String str4 = rs.getString("ReviewerName");
        int k = rs.getInt("ReviewRating");
        System.out.println(str4 + "\t" + k);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void gamePlatform() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select GName, PlatName from GAME, PLATFORM_SET, PLATFORM where GId = GamePlatId and PlatformId = PlatId order by PlatName";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("Platform\tTitle");
      System.out.println("--------\t-----");
      while (rs.next()) {
        String gname = rs.getString("GName");
        String platname = rs.getString("PlatName");
        System.out.println(gname + "\t" + platname);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void dateRange() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Scanner instream = new Scanner(System.in);
      System.out.print("Please enter a beginning year: ");
      int i = instream.nextInt();
      System.out.print("Please enter an ending year: ");
      int j = instream.nextInt();

      Statement stmt = conn.createStatement();
      String qry = "select GName, ReleaseDate from GAME where ReleaseDate >= " + i + " and ReleaseDate <= " + j + " order by GId";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("\nTitles in Date Range\tYear");
      System.out.println("--------------------\t----");
      while (rs.next()) {
        String gname = rs.getString("GName");
        int releasedate = rs.getInt("ReleaseDate");
        System.out.println(gname + "\t" + releasedate);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void reviewRange() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Scanner instream = new Scanner(System.in);
      System.out.print("Please enter a minimum review rating: ");
      int i = instream.nextInt();
      System.out.print("Please enter a maximum review rating: ");
      int j = instream.nextInt();

      Statement stmt = conn.createStatement();
      String qry = "select GName, ReviewerName, ReviewRating from GAME, REVIEW, REVIEWER where GId = GameRevId and ReviewerNum = ReviewerId and ReviewRating >= " + i + " and ReviewRating <= " + j + " order by GName";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("\nTitles in Review Range\tReviewer\tRating");
      System.out.println("----------------------\t--------\t------");
      while (rs.next()) {
        String gname = rs.getString("GName");
        String reviewername = rs.getString("ReviewerName");
        int reviewrating = rs.getInt("ReviewRating");
        System.out.println(gname + "\t" + reviewername + "\t" + reviewrating);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void ratingSearch() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Scanner instream = new Scanner(System.in);
      System.out.print("Please enter an ESRB rating: ");
      String rate = instream.nextLine();

      Statement stmt = conn.createStatement();
      String qry = "select GName, Rating, ReleaseDate from GAME where Rating = '" + rate + "' order by GName";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("\nTitles with ESRB Rating\tRating\tReleaseDate");
      System.out.println("-----------------------\t------\t-----------");
      while (rs.next()) {
        String gname = rs.getString("GName");
        String rating = rs.getString("Rating");
        int releasedate = rs.getInt("ReleaseDate");
        System.out.println(gname + "\t" + rating + "\t" + releasedate);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void searchCharacters() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select CharId, CharName from MAIN_CHARACTERS order by CharId";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("ID\tCharacter");
      System.out.println("--\t---------");
      while (rs.next()) {
        int i = rs.getInt("CharId");
        String charname = rs.getString("CharName");
        System.out.println(i + "\t" + charname);
      }
      System.out.print("\n");
      rs.close();

      System.out.print("Please select a character to view game appearances: ");
      Scanner instream = new Scanner(System.in);
      int j = instream.nextInt();

      qry = "select GName, ReleaseDate from GAME, SET_CHARACTER, MAIN_CHARACTERS where GId = GameCharId and CharacterId = CharId and CharId = " + j + " order by ReleaseDate";
      rs = stmt.executeQuery(qry);

      System.out.println("\nAppearances\tRelease Date");
      System.out.println("-----------\t------------");
      while (rs.next()) {
        String str4 = rs.getString("GName");
        int k = rs.getInt("ReleaseDate");
        System.out.println(str4 + "\t" + k);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void genreSearch() {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select GenreId, GenreName from GENRE order by GenreId";
      ResultSet rs = stmt.executeQuery(qry);

      System.out.println("ID\tGenre");
      System.out.println("--\t-----");
      while (rs.next()) {
        int i = rs.getInt("GenreId");
        String genrename = rs.getString("GenreName");
        System.out.println(i + "\t" + genrename);
      }
      System.out.print("\n");
      rs.close();

      System.out.print("Please select a genre to view the corresponding games: ");
      Scanner instream = new Scanner(System.in);
      int j = instream.nextInt();

      qry = "select GName, Rating, ReleaseDate from GAME, GENRE where GenreType = GenreId and GenreId = " + j + " order by ReleaseDate";
      rs = stmt.executeQuery(qry);

      System.out.println("\nSelected Titles\tRating\tRelease Date");
      System.out.println("---------------\t------\t------------");
      while (rs.next()) {
        String str4 = rs.getString("GName");
        String str5 = rs.getString("Rating");
        int k = rs.getInt("ReleaseDate");
        System.out.println(str4 + "\t" + str5 + "\t" + k);
      }
      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void insertGame()
  {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select count(GId) from GAME";
      ResultSet rs = stmt.executeQuery(qry);

      int games = 1;
      while (rs.next())
      {
        games = rs.getInt("1");
      }
      games++;

      Scanner instream = new Scanner(System.in);
      System.out.print("Please enter the new game title: ");
      String title = instream.nextLine();
      System.out.print("\nPlease enter the ESRB rating: ");
      String rating = instream.nextLine();
      System.out.print("\nPlease enter the developer ID: ");
      int developer = instream.nextInt();
      System.out.print("\nPlease enter the publisher ID: ");
      int publisher = instream.nextInt();
      System.out.print("\nPlease enter the genre ID: ");
      int genre = instream.nextInt();
      System.out.print("\nPlease enter the release year: ");
      int year = instream.nextInt();

      String cmd = "insert into GAME values (" + games + ", '" + title + "', " + developer + ", " + publisher + ", " + genre + ", '" + rating + "', " + year + ")";
      stmt.executeUpdate(cmd);

      System.out.print("\n");
      rs.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void insertDeveloper()
  {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select count(DevId) from DEVELOPER";
      ResultSet rs = stmt.executeQuery(qry);

      int developers = 1;
      while (rs.next())
      {
        developers = rs.getInt("1");
      }
      developers++;

      Scanner instream = new Scanner(System.in);
      System.out.print("Please enter the new developer's name: ");
      String developername = instream.nextLine();

      String cmd = "insert into DEVELOPER values (" + developers + ", '" + developername + "')";
      stmt.executeUpdate(cmd);

      System.out.print("\n");
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void insertPublisher()
  {
    Connection conn = null;
    try {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);

      Statement stmt = conn.createStatement();
      String qry = "select count(PubId) from PUBLISHER";
      ResultSet rs = stmt.executeQuery(qry);

      int publishers = 1;
      while (rs.next())
      {
        publishers = rs.getInt("1");
      }
      publishers++;

      Scanner instream = new Scanner(System.in);
      System.out.print("Please enter the new publisher's name: ");
      String publishername = instream.nextLine();

      String cmd = "insert into PUBLISHER values (" + publishers + ", '" + publishername + "')";
      stmt.executeUpdate(cmd);

      System.out.print("\n");
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (conn != null)
          conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}