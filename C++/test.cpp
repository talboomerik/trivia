#define CATCH_CONFIG_MAIN
#include "catch.hpp"
#include "Game.hpp"

/*given player not in penalty box then reward player and go to next player
given player is leaving the penalty box then reward player and go to next player
given player is not leaving the penalty box then go to next player*/
SCENARIO( "question is correctly answered", "[vector]" ) {
   
  Game game;
  game.add("dummy");

  GIVEN( "player is in penalty box and not leaving" ) {
    game.wrongAnswer();
    REQUIRE();
    WHEN( "verifying" ) {
      THEN( "go to next player" ) {
       REQUIRE();
      }
    }
  }
}
