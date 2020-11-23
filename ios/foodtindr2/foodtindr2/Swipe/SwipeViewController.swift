import UIKit

import Alamofire
import SwiftyJSON

struct CardsDataModel {
    
    var bgColor: UIColor
    var text : String
    var image : String
    var roomCode : String
      
    init(bgColor: UIColor, text: String, image: String, roomCode:String) {
        self.bgColor = bgColor
        self.text = text
        self.image = image
        self.roomCode = roomCode
    }
}

class SwipeViewController: UIViewController {

    //MARK: - Properties
    var viewModelData:[CardsDataModel] = []
    var stackContainer : StackContainerView!
    
    var roomCode: String = "";
    
    //MARK: - Init
    private var timer: Timer!
    
    override func loadView() {
        view = UIView()
        view.backgroundColor = UIColor(red:0.93, green:0.93, blue:0.93, alpha:1.0)
        stackContainer = StackContainerView()
        view.addSubview(stackContainer)
        configureStackContainer()
        stackContainer.translatesAutoresizingMaskIntoConstraints = false
        configureNavigationBarButtonItem()
    }
 
    override func viewDidLoad() {
        super.viewDidLoad()
        title = "Food Tindr"
        
        var request = URLRequest(url: URL(string: ConfigVariables.GET_RESTAURANTS + "?roomCode=" + roomCode)!)
        request.httpMethod = HTTPMethod.get.rawValue
        request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")
        request.timeoutInterval = 2
        AF.request(request).response { response in
            if (response.response?.statusCode != 200) {
                print(response)
            } else {
                let responseJSON = JSON(response.data!)
                
                for (_,value) in responseJSON {
                    let restaurantName:String = value["restaurantName"].string!
                    let pictureURL:String = value["pictureURL"].string!
                    let model:CardsDataModel = CardsDataModel(bgColor: UIColor(red:0.96, green:0.81, blue:0.46, alpha:1.0),
                                                              text: restaurantName,
                                                              image: pictureURL,
                                                              roomCode: self.roomCode)
                    self.viewModelData.append(model)
                    print(restaurantName, pictureURL)
                    self.stackContainer.dataSource = self
                }
            }
        }
        
        timer = Timer.scheduledTimer(withTimeInterval: 1.0, repeats: true) { (Timer) in
            self.winningRestaurantCall()
        }
    }
    
    private func winningRestaurantCall() {
        var request = URLRequest(url: URL(string: ConfigVariables.WINNING_RESTAURANTS + "?roomCode=" + roomCode)!)
        request.httpMethod = HTTPMethod.get.rawValue
        request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")
        request.timeoutInterval = 2
        AF.request(request).response { response in
            if (response.response?.statusCode != 200) {
                print(response)
            } else {
                let responseJSON = JSON(response.data!)
                
                let winningRestaurant:String = responseJSON["winningRestaurant"].string!
                if (winningRestaurant != "") {
                    self.timer.invalidate()
                    self.presentWinningAlert(restaurant: winningRestaurant)
                }
            }
        }
    }
    
    private func presentWinningAlert(restaurant:String) {
        let alert = UIAlertController(title: "Restaurant Winner! ", message: restaurant, preferredStyle: .alert)
        
        let action = UIAlertAction(title: "Ok", style: .default) { (action) in
            return
        }
        
        alert.addAction(action)
        
        present(alert, animated: true, completion: nil)
    }
 
    //MARK: - Configurations
    func configureStackContainer() {
        stackContainer.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        stackContainer.centerYAnchor.constraint(equalTo: view.centerYAnchor, constant: -60).isActive = true
        stackContainer.widthAnchor.constraint(equalToConstant: 300).isActive = true
        stackContainer.heightAnchor.constraint(equalToConstant: 400).isActive = true
    }
    
    func configureNavigationBarButtonItem() {
        navigationItem.rightBarButtonItem = UIBarButtonItem(title: "Reset", style: .plain, target: self, action: #selector(resetTapped))
    }
    
    //MARK: - Handlers
    @objc func resetTapped() {
        stackContainer.reloadData()
    }

}

extension SwipeViewController : SwipeCardsDataSource {

    func numberOfCardsToShow() -> Int {
        return viewModelData.count
    }
    
    func card(at index: Int) -> SwipeCardView {
        let card = SwipeCardView()
        card.dataSource = viewModelData[index]
        return card
    }
    func emptyView() -> UIView? {
        return nil
    }
}

protocol SwipeCardsDataSource {
    func numberOfCardsToShow() -> Int
    func card(at index: Int) -> SwipeCardView
    func emptyView() -> UIView?
}

protocol SwipeCardsDelegate {
    func swipeDidEnd(on view: SwipeCardView, direction: String)
}

