import java.io.PrintStream;
import java.sql.*;
import java.util.Scanner;
import org.apache.derby.jdbc.ClientDriver;

public class VideoGame
{
  public static void main(String[] paramArrayOfString)
  {
    Connection conn = null;
    try
    {
      Driver d = new ClientDriver();
      String url = "jdbc:derby://localhost/videogamedb;create=false";
      conn = d.connect(url, null);
 
      Scanner instream = new Scanner(System.in);
      System.out.println("\nWelcome to the Video Game Database!\n");
      System.out.println("Are you an Administrator or a User?\n");
      System.out.print("1 - Administrator\n2 - User\n\nPlease enter a selection: ");
      int selection = instream.nextInt();

      if (selection == 1)
        adminMenu(conn);
      else if (selection == 2)
        userMenu(conn);
      else
        System.out.println("\nInvalid entry, try again.\n");
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        if(conn != null)
          conn.close();
      }
      catch(SQLException e)
      {
        e.printStackTrace();
      }
    }
  }

  public static void adminMenu(Connection conn) throws SQLException
  {
    Scanner instream = new Scanner(System.in);
    int selection = 1;

    while (selection != 0)
    {
      System.out.println("\nAdministrator Menu");
      System.out.println("------------------\n");
      System.out.println("1 - View Games\t\t6 - View Platforms\t11 - Release Date Search\t16 - Add a Game");
      System.out.println("2 - View Developers\t7 - View Characters\t12 - Review Score Search\t17 - Add a Developer");
      System.out.println("3 - View Publishers\t8 - Select Platform\t13 - ESRB Rating Search\t\t18 - Add a Publisher");
      System.out.println("4 - View Reviews\t9 - Game Reviews\t14 - Character Search\t\t19 - Add a Review");
      System.out.println("5 - View Reviewers\t10 - Games by Platform\t15 - Genre Search");
      System.out.println("\n0 - Exit");
      System.out.print("\nPlease select an option: ");
      selection = instream.nextInt();
      System.out.println();

      switch (selection) { 
      case 0:
        System.out.println("Thanks for using the Video Game Database!"); break;
      case 1:
        viewGames(conn); break;
      case 2:
        viewDevelopers(conn); break;
      case 3:
        viewPublishers(conn); break;
      case 4:
        viewReviews(conn); break;
      case 5:
        viewReviewers(conn); break;
      case 6:
        viewPlatforms(conn); break;
      case 7:
        viewCharacters(conn); break;
      case 8:
        selectPlatform(conn); break;
      case 9:
        gameReviews(conn); break;
      case 10:
        gamePlatform(conn); break;
      case 11:
        dateRange(conn); break;
      case 12:
        reviewRange(conn); break;
      case 13:
        ratingSearch(conn); break;
      case 14:
        searchCharacters(conn); break;
      case 15:
        genreSearch(conn); break;
      case 16:
        insertGame(conn); break;
      case 17:
        insertDeveloper(conn); break;
      case 18:
        insertPublisher(conn); break;
      case 19:
	 addReview(conn); break;
      default:
        System.out.println("Invalid option, please try again.\n");
      }
    }
  }

  public static void userMenu(Connection conn) throws SQLException
  {
    Scanner instream = new Scanner(System.in);
    int selection = 1;

    while (selection != 0)
    {
      System.out.println("\nUser Menu");
      System.out.println("---------\n");
      System.out.println("1 - View Games\t\t6 - View Platforms\t11 - Release Date Search");
      System.out.println("2 - View Developers\t7 - View Characters\t12 - Review Score Search");
      System.out.println("3 - View Publishers\t8 - Select Platform\t13 - ESRB Rating Search");
      System.out.println("4 - View Reviews\t9 - Game Reviews\t14 - Character Search");
      System.out.println("5 - View Reviewers\t10 - Games by Platform\t15 - Genre Search");
      System.out.println("\n0 - Exit");
      System.out.print("\nPlease select an option: ");
      selection = instream.nextInt();
      System.out.println();

      switch (selection) { 
      case 0:
        System.out.println("Thanks for using the Video Game Database!"); break;
      case 1:
        viewGames(conn); break;
      case 2:
        viewDevelopers(conn); break;
      case 3:
        viewPublishers(conn); break;
      case 4:
        viewReviews(conn); break;
      case 5:
        viewReviewers(conn); break;
      case 6:
        viewPlatforms(conn); break;
      case 7:
        viewCharacters(conn); break;
      case 8:
        selectPlatform(conn); break;
      case 9:
        gameReviews(conn); break;
      case 10:
        gamePlatform(conn); break;
      case 11:
        dateRange(conn); break;
      case 12:
        reviewRange(conn); break;
      case 13:
        ratingSearch(conn); break;
      case 14:
        searchCharacters(conn); break;
      case 15:
        genreSearch(conn); break;
      default:
        System.out.println("Invalid option, please try again.\n"); }
    }
  }

  public static void viewGames(Connection conn) throws SQLException {
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
    rs.close();
  }

  public static void viewDevelopers(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select DevId, DevName from DEVELOPER order by DevId";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("ID\tDevelopers");
    System.out.println("--\t----------");
    while (rs.next()) {
	  int devid = rs.getInt("DevId");
      String devname = rs.getString("DevName");
      System.out.println(devid + "\t" + devname);
    }
    rs.close();
  }

  public static void viewPublishers(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select PubId, PubName from PUBLISHER order by PubId";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("ID\tPublishers");
    System.out.println("--\t----------");
    while (rs.next()) {
  	  int pubid = rs.getInt("PubId");
      String pubname = rs.getString("PubName");
      System.out.println(pubid + "\t" + pubname);
    }
    rs.close();
  }

  public static void viewGenres(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select GenreId, GenreName from GENRE order by GenreId";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("ID\tGenre");
    System.out.println("--\t-----");
    while (rs.next()) {
      int genreid = rs.getInt("GenreId");
	  String genrename = rs.getString("GenreName");
	  System.out.println(genreid + "\t" + genrename);
    }
    rs.close();
  }
  
  public static void viewReviews(Connection conn) throws SQLException {
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
    rs.close();
  }

  public static void viewReviewers(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select ReviewerName from REVIEWER order by ReviewerName";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("Reviewers");
    System.out.println("---------");
    while (rs.next()) {
      String reviewername = rs.getString("ReviewerName");
      System.out.println(reviewername);
    }
    rs.close();
  }

  public static void viewPlatforms(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select PlatName from PLATFORM order by PlatName";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("Platforms");
    System.out.println("---------");
    while (rs.next()) {
      String platname = rs.getString("PlatName");
      System.out.println(platname);
    }
    rs.close();
  }

  public static void viewCharacters(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select CharName from MAIN_CHARACTERS order by CharName";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("Main Characters");
    System.out.println("---------------");
    while (rs.next()) {
      String charname = rs.getString("CharName");
      System.out.println(charname);
    }
    rs.close();
  }

  public static void selectPlatform(Connection conn) throws SQLException {
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
    int selection = instream.nextInt();

    qry = "select GName from GAME, PLATFORM_SET, PLATFORM where GId = GamePlatId and PlatformId =" + selection + " group by GName order by GName";
    rs = stmt.executeQuery(qry);

    System.out.println("\nSelected Titles");
    System.out.println("---------------");
    while (rs.next()) {
      String gname = rs.getString("GName");
      System.out.println(gname);
    }
    rs.close();
  }

  public static void gameReviews(Connection conn) throws SQLException {
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
    int selection = instream.nextInt();

    qry = "select ReviewerName, ReviewRating from GAME, REVIEW, REVIEWER where GId = GameRevId and ReviewerNum = ReviewerId and GId = " + selection + " order by ReviewerName";
    rs = stmt.executeQuery(qry);

    System.out.println("\nReviewer\tRating");
    System.out.println("--------\t------");
    while (rs.next()) {
      String reviewername = rs.getString("ReviewerName");
      int reviewrating = rs.getInt("ReviewRating");
      System.out.println(reviewername + "\t" + reviewrating);
    }
    rs.close();
  }

  public static void gamePlatform(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select GName, PlatName from GAME, PLATFORM_SET, PLATFORM where GId = GamePlatId and PlatformId = PlatId order by PlatName, GName";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("Platform\t\tTitle");
    System.out.println("--------\t\t-----");
    while (rs.next()) {
      String gname = rs.getString("GName");
      String platname = rs.getString("PlatName");
      System.out.println(gname + "\t\t" + platname);
    }
    rs.close();
  }

  public static void dateRange(Connection conn) throws SQLException {
    Scanner instream = new Scanner(System.in);
    System.out.print("Please enter a beginning year: ");
    int selection1 = instream.nextInt();
    System.out.print("Please enter an ending year: ");
    int selection2 = instream.nextInt();

    Statement stmt = conn.createStatement();
    String qry = "select GName, ReleaseDate from GAME where ReleaseDate >= " + selection1 + " and ReleaseDate <= " + selection2 + " order by GId";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("\nTitles in Date Range\tYear");
    System.out.println("--------------------\t----");
    while (rs.next()) {
      String gname = rs.getString("GName");
      int releasedate = rs.getInt("ReleaseDate");
      System.out.println(gname + "\t" + releasedate);
    }
    rs.close();
  }

  public static void reviewRange(Connection conn) throws SQLException {
    Scanner instream = new Scanner(System.in);
    System.out.print("Please enter a minimum review rating: ");
    int selection1 = instream.nextInt();
    System.out.print("Please enter a maximum review rating: ");
    int selection2 = instream.nextInt();

    Statement stmt = conn.createStatement();
    String qry = "select GName, ReviewerName, ReviewRating from GAME, REVIEW, REVIEWER where GId = GameRevId and ReviewerNum = ReviewerId and ReviewRating >= " + selection1 + " and ReviewRating <= " + selection2 + " order by GName";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("\nTitles in Review Range\tReviewer\tRating");
    System.out.println("----------------------\t--------\t------");
    while (rs.next()) {
      String gname = rs.getString("GName");
      String reviewername = rs.getString("ReviewerName");
      int reviewrating = rs.getInt("ReviewRating");
      System.out.println(gname + "\t" + reviewername + "\t" + reviewrating);
    }
    rs.close();
  }

  public static void ratingSearch(Connection conn) throws SQLException {
    Scanner instream = new Scanner(System.in);
    System.out.print("Please enter an ESRB rating: ");
    String selection = instream.nextLine();

    Statement stmt = conn.createStatement();
    String qry = "select GName, Rating, ReleaseDate from GAME where Rating = '" + selection + "' order by GName";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("\nTitles with ESRB Rating\tRating\tReleaseDate");
    System.out.println("-----------------------\t------\t-----------");
    while (rs.next()) {
      String gname = rs.getString("GName");
      String rating = rs.getString("Rating");
      int releasedate = rs.getInt("ReleaseDate");
      System.out.println(gname + "\t" + rating + "\t" + releasedate);
    }
    rs.close();
  }

  public static void searchCharacters(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select CharId, CharName from MAIN_CHARACTERS order by CharId";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("ID\tCharacter");
    System.out.println("--\t---------");
    while (rs.next()) {
      int charid = rs.getInt("CharId");
      String charname = rs.getString("CharName");
      System.out.println(charid + "\t" + charname);
    }
    System.out.print("\n");
    rs.close();

    System.out.print("Please select a character to view game appearances: ");
    Scanner instream = new Scanner(System.in);
    int selection = instream.nextInt();

    qry = "select GName, ReleaseDate from GAME, SET_CHARACTER, MAIN_CHARACTERS where GId = GameCharId and CharacterId = CharId and CharId = " + selection + " order by ReleaseDate";
    rs = stmt.executeQuery(qry);

    System.out.println("\nAppearances\tRelease Date");
    System.out.println("-----------\t------------");
    while (rs.next()) {
      String gname = rs.getString("GName");
      int releasedate = rs.getInt("ReleaseDate");
      System.out.println(gname + "\t" + releasedate);
    }
    rs.close();
  }

  public static void genreSearch(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select GenreId, GenreName from GENRE order by GenreId";
    ResultSet rs = stmt.executeQuery(qry);

    System.out.println("ID\tGenre");
    System.out.println("--\t-----");
    while (rs.next()) {
      int genreid = rs.getInt("GenreId");
      String genrename = rs.getString("GenreName");
      System.out.println(genreid + "\t" + genrename);
    }
    System.out.print("\n");
    rs.close();

    System.out.print("Please select a genre to view the corresponding games: ");
    Scanner instream = new Scanner(System.in);
    int selection = instream.nextInt();

    qry = "select GName, Rating, ReleaseDate from GAME, GENRE where GenreType = GenreId and GenreId = " + selection + " order by ReleaseDate";
    rs = stmt.executeQuery(qry);

    System.out.println("\nSelected Titles\tRating\tRelease Date");
    System.out.println("---------------\t------\t------------");
    while (rs.next()) {
      String gname = rs.getString("GName");
      String rating = rs.getString("Rating");
      int releasedate = rs.getInt("ReleaseDate");
      System.out.println(gname + "\t" + rating + "\t" + releasedate);
    }
    rs.close();
  }

  public static void insertGame(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select count(GId) from GAME";
    ResultSet rs = stmt.executeQuery(qry);

    int games = 1;
    while (rs.next())
    {
      games = rs.getInt("1");
    }
    games++;
    rs.close();

    Scanner instream = new Scanner(System.in);
    System.out.print("Please enter the new game title: ");
    String title = instream.nextLine();
    System.out.print("\nPlease enter the ESRB rating: ");
    String rating = instream.nextLine();
    System.out.print("\n");
    viewDevelopers(conn);
    System.out.print("Please enter the developer ID: ");
    int developer = instream.nextInt();
    System.out.print("\n");
    viewPublishers(conn);
    System.out.print("Please enter the publisher ID: ");
    int publisher = instream.nextInt();
    System.out.print("\n");
    viewGenres(conn);
    System.out.print("Please enter the genre ID: ");
    int genre = instream.nextInt();
    System.out.print("\nPlease enter the release year: ");
    int year = instream.nextInt();

    String cmd = "insert into GAME values (" + games + ", '" + title + "', " + developer + ", " + publisher + ", " + genre + ", '" + rating + "', " + year + ")";
    stmt.executeUpdate(cmd);
  }

  public static void insertDeveloper(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select count(DevId) from DEVELOPER";
    ResultSet rs = stmt.executeQuery(qry);

    int developers = 1;
    while (rs.next())
    {
      developers = rs.getInt("1");
    }
    developers++;
    rs.close();

    Scanner instream = new Scanner(System.in);
    System.out.print("Please enter the new developer's name: ");
    String developername = instream.nextLine();

    String cmd = "insert into DEVELOPER values (" + developers + ", '" + developername + "')";
    stmt.executeUpdate(cmd);
  }

  public static void insertPublisher(Connection conn) throws SQLException {
    Statement stmt = conn.createStatement();
    String qry = "select count(PubId) from PUBLISHER";
    ResultSet rs = stmt.executeQuery(qry);

    int publishers = 1;
    while (rs.next())
    {
      publishers = rs.getInt("1");
    }
    publishers++;
    rs.close();

    Scanner instream = new Scanner(System.in);
    System.out.print("Please enter the new publisher's name: ");
    String publishername = instream.nextLine();

    String cmd = "insert into PUBLISHER values (" + publishers + ", '" + publishername + "')";
    stmt.executeUpdate(cmd);
  }

  public static void addReview(Connection conn) throws SQLException {
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

    System.out.print("Please select a game to add a review: ");
    Scanner instream = new Scanner(System.in);
    int selection1 = instream.nextInt();

    qry = "select ReviewerId, ReviewerName from REVIEWER order by ReviewerId";
    rs = stmt.executeQuery(qry);

    System.out.println("\nID\tReviewer");
    System.out.println("--\t--------");
    while (rs.next()) {
	int reviewerid = rs.getInt("ReviewerId");
	String reviewername = rs.getString("ReviewerName");
	System.out.println(reviewerid + "\t" + reviewername);
    }
    rs.close();

    System.out.print("\nPlease select a reviewer: ");
    int selection2 = instream.nextInt();

    System.out.print("\nPlease enter the score (1-10): ");
    int selection3 = instream.nextInt();

    String cmd = "insert into REVIEW values (" + selection1 + ", " + selection2 + ", " + selection3 + ")";
    stmt.executeUpdate(cmd);
  }
}
