#define CATCH_CONFIG_MAIN
#include "catch.hpp"
#include "Game.h"

//given player is leaving the penalty box then reward player and go to next player

//given player not in penalty box then reward player and go to next player
//given player is not leaving the penalty box then go to next player
SCENARIO( "question is correctly answered", "[vector]" ) {  
  Game game;
  const std::string player1="dummy";
  const std::string player2="smartie";
  game.add(player1);
  game.add(player2);

  GIVEN( "player is in penalty box and not leaving" ) {
    game.wasIncorrectlyAnsweredAndGoToNextPlayer();
    game.wasIncorrectlyAnsweredAndGoToNextPlayer();
    REQUIRE(game.getCurrentPlayerName() == player1);

    WHEN( "verifying" ) {
    	auto win = game.wasCorrectlyAnsweredAndGoToNextPlayerAndReturnIfWeHaveAWinner();
      THEN( "go to next player" ) {
    	  REQUIRE(game.getCurrentPlayerName() == player2);
      }
    }
  }

  GIVEN("player is not in penalty box") {
    auto const & purse = game.getCurrentPlayerPurse();
    auto startPurse = purse;
    WHEN( "verifying") {
      auto win = game.wasCorrectlyAnsweredAndGoToNextPlayerAndReturnIfWeHaveAWinner();
      THEN ("reward the current player") {
        REQUIRE(purse == startPurse+1);
      }
      THEN( "go to next player" ) {
    	  REQUIRE(game.getCurrentPlayerName() == player2);
      }
      
    }
  }
}
