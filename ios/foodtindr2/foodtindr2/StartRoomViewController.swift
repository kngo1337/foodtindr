import UIKit
import Alamofire
import SwiftyJSON

class StartRoomViewController: UIViewController, UITextFieldDelegate  {

    var originalPositionY: CGFloat = 0; // this contains the original Y position of the frame after the nav bar
    
    
    @IBOutlet weak var enterNameTextField: UITextField!
    @IBOutlet weak var createRoomBtn: UIButton!
    
    @IBOutlet weak var enterRoomCode: UITextField!
    @IBOutlet weak var joinRoomButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        enterNameTextField.delegate = self
        enterRoomCode.delegate = self
    }
    
    @IBAction func createRoomPressed(_ sender: Any) {
        print("Create Room")
        
        var request = URLRequest(url: URL(string: ConfigVariables.CREATE_ROOM + "?playerName=" + enterNameTextField.text!)!)
        request.httpMethod = HTTPMethod.get.rawValue
        request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")
        request.timeoutInterval = 2
        
        AF.request(request).responseJSON { response in
            if (response.response?.statusCode != 200) {
                print(response)
            } else {
                let responseJSON = JSON(response.data!)
                let roomCode = responseJSON["roomCode"].string
                print(roomCode!);
                self.enterRoomCode.text = roomCode
                self.goToNextRoom()
            }
        }
    }
    
    func goToNextRoom() {
        self.performSegue(withIdentifier: "goToRoom", sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "goToRoom" {
            let destionationVC = segue.destination as! JoinRoomTableViewController
            destionationVC.roomCode = enterRoomCode.text!
        }
    }
    
    @IBAction func joinRoomPressed(_ sender: Any) {
        print("Join Room Pressed")
        var request = URLRequest(url: URL(string: ConfigVariables.JOIN_ROOM + "?playerName=" + enterNameTextField.text!
                                            + "&roomCode=" + enterRoomCode.text!)!)
        request.httpMethod = HTTPMethod.get.rawValue
        request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")
        request.timeoutInterval = 2
        
        AF.request(request).response { response in
            if (response.response?.statusCode != 200) {
                print(response)
            } else {
                self.goToNextRoom()
            }
        }
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        print(textField.text!)
        textField.text = ""
    }
    
    // end editing on any textfield by touching outside
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    @objc func keyboardWillShow(notification: NSNotification) {
        self.view.frame.origin.y = -100 // Move view 100 points upwards
    }

    @objc func keyboardWillHide(notification: NSNotification) {
        self.view.frame.origin.y = self.originalPositionY // move view to original position
    }
    
    // this method allows the keyboard to go to the next text field, closes keyboard if none
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        let nextTag = textField.tag + 1
        // Try to find next responder
        let nextResponder = textField.superview?.viewWithTag(nextTag) as UIResponder?

        if nextResponder != nil {
            // Found next responder, so set it
            nextResponder?.becomeFirstResponder()
        } else {
            // Not found, so remove keyboard
            textField.resignFirstResponder()
        }
        return false
    }
}
