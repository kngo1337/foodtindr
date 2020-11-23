
import UIKit
import Alamofire
import SwiftyJSON

class JoinRoomTableViewController: UITableViewController {

    var roomCode: String = "";
    
    @IBOutlet weak var roomCodeLabel: UILabel!
    var personArray = [String]()
    
    private var timer: Timer!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        roomCodeLabel.text = "Room Code: " + roomCode
        print("Join Room Code: ", roomCode)
        refreshPerson()
        
        timer = Timer.scheduledTimer(withTimeInterval: 1.0, repeats: true) { (Timer) in
            self.refreshPerson()
        }
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: .default, reuseIdentifier: "PlayerCell")
        cell.textLabel?.text = personArray[indexPath.row]
        return cell;
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return personArray.count
    }
    
    func gameHasStarted() {
        print("Game has Started")
        timer.invalidate()
        let swipeViewController = SwipeViewController()
        swipeViewController.roomCode = roomCode
        swipeViewController.modalPresentationStyle = .fullScreen
        self.present(swipeViewController, animated: true)
    }
    
    @IBAction func startGame(_ sender: Any) {
        var request = URLRequest(url: URL(string: ConfigVariables.START_GAME + "?roomCode=" + roomCode)!)
        request.httpMethod = HTTPMethod.get.rawValue
        request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")
        request.timeoutInterval = 2
        
        AF.request(request).responseJSON { response in
            if (response.response?.statusCode != 200) {
                print(response)
            }
        }
    }
    func refreshPerson() {
        var request = URLRequest(url: URL(string: ConfigVariables.GET_PLAYERS + "?roomCode=" + roomCode)!)
        request.httpMethod = HTTPMethod.get.rawValue
        request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")
        request.timeoutInterval = 2
        
        AF.request(request).responseJSON { response in
            if (response.response?.statusCode != 200) {
                print(response)
            } else {
                let responseJSON = JSON(response.data!)
                let gameStart = responseJSON["gameStarted"].boolValue
                if (gameStart) {
                    self.gameHasStarted()
                } else {
                    let playerList = responseJSON["players"].arrayValue.map{$0.stringValue}
                    print(playerList)
                    self.personArray = playerList
                    self.tableView.reloadData()
                }
                
            }
        }
        
    }

}
