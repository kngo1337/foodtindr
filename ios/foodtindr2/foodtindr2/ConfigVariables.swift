import Foundation

class ConfigVariables {
    static let API_URL = "http://localhost:8000/"
    static let CREATE_ROOM = API_URL + "createRoom"
    static let JOIN_ROOM = API_URL + "joinRoom"
    
    static let GET_PLAYERS = API_URL + "getPlayers"
    static let START_GAME = API_URL + "startGame"
    static let GET_RESTAURANTS = API_URL + "getRestaurantList"
    
    static let CONFIRM_RESTAURANTS = API_URL + "confirmRestaurants"
    static let WINNING_RESTAURANTS = API_URL + "winningRestaurant"
    
}
