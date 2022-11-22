package guessNumberGame.data;

import junit.framework.TestCase;
import guessNumberGame.Service.GameService;
import guessNumberGame.TestApplicationConfiguration;
import guessNumberGame.models.Game;
import guessNumberGame.models.Round;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)

public class GameDatabaseDaoTest extends TestCase {
    @Autowired
    GameDao gameDao;

   @Autowired
   RoundDao roundDao;
   
   
   public GameDatabaseDaoTest()       
 {}

  /* @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }  */
 
    @Before
       public void setUp() {    //to clean up everything from memory
        List<Round> rounds = roundDao.getAll();
        for(Round round : rounds) {
            roundDao.deleteById(round.getId());
        }

        List<Game> games = gameDao.getAll();
        for(Game game : games) {
            gameDao.deleteById(game.getGameId());
        }
    }

    //@After
    //@Override
   // public void tearDown() throws Exception {    }


    @Test
    public void testAddGetGame() {
        // adds new game using dao
        GameService gameService = new GameService();
        Game game = gameService.newGame();
        gameDao.add(game);

        Game fromDao = gameDao.findById(game.getGameId());
        assertEquals(game.getGameId(), fromDao.getGameId());
    }

    @Test
    public void testGetAll() {
       GameService gameService = new GameService();
       Game game = gameService.newGame();
       Game game2 = gameService.newGame();
       gameDao.add(game);
       gameDao.add(game2);
       
       List<Game> games = gameDao.getAll();
       
       assertEquals(2, games.size());
       assertTrue(games.contains(game));
       assertTrue(games.contains(game2));
    }


    @Test
    public void testUpdate() {
        GameService gameService = new GameService();
        Game game = gameService.newGame();
        gameDao.add(game);
        game.setIsFinished(true);
        gameDao.update(game);
        Game updated = gameDao.findById(game.getGameId());
        assertTrue(updated.getIsFinished());
    }

    @Test
    public void testDeleteById() {
         GameService gameService = new GameService();
         Game game = gameService.newGame();
         Game game2 = gameService.newGame();
         
         gameDao.add(game);
         gameDao.add(game2);
         
         gameDao.deleteById(game.getGameId());
         
         List<Game> games = gameDao.getAll();
         assertEquals(1, games.size());
         
    }

    /**
     * Test of add method, of class GameDatabaseDao.
     
    @Test
    public void testAdd() {
        System.out.println("add");
        Game game = null;
        GameDatabaseDao instance = null;
        Game expResult = null;
        Game result = instance.add(game);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class GameDatabaseDao.
    
    @Test
    public void testFindById() {
        System.out.println("findById");
        int game_id = 0;
        GameDatabaseDao instance = null;
        Game expResult = null;
        Game result = instance.findById(game_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
}
