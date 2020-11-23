
import UIKit
import Alamofire
import SwiftyJSON

class JoinRoomTableViewController: UITableViewController {

    var roomCode: String = "";
    
    var personArray = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        roomCode = "ABCD"
        print("Join Room Code: ", roomCode)
        refreshPerson()
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: .default, reuseIdentifier: "PlayerCell")
        cell.textLabel?.text = personArray[indexPath.row]
        return cell;
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return personArray.count
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
                let playerList = responseJSON["players"].arrayValue.map{$0.stringValue}
                print(playerList)
                self.personArray = playerList
                self.tableView.reloadData()
            }
        }
        
    }

}
