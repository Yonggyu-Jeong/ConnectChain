import 'package:flutter/material.dart';
import '../../models/goods.dart';
import '../../dummy_data.dart';

class WatchListScreen extends StatefulWidget {
  static const routeName = '/watch-list';

  @override
  State<WatchListScreen> createState() => _WatchListScreenState();
}

class _WatchListScreenState extends State<WatchListScreen> {
  final likes = DUMMY_GOODS;
  // final List<Goods> likes = [
  //   Goods(
  //       id: "id",
  //       imagePath: "assets/images/macbook.jpg",
  //       title: "맥북 프로 팝니다.",
  //       location: "성수2동",
  //       time: "7일전",
  //       desc: "desc",
  //       price: "1,500,000원",
  //       likes: 35,
  //       like: true,
  //       category: "category",
  //       item_status: 0,
  //       user_id: "user_id"),
  //   Goods(
  //       id: "id",
  //       imagePath: "assets/images/macbook2.jpg",
  //       title: "맥북 싸게 팔아요",
  //       location: "합정동",
  //       time: "8시간 전",
  //       desc: "desc",
  //       price: "1,000,000원",
  //       likes: 17,
  //       like: true,
  //       category: "category",
  //       item_status: 0,
  //       user_id: "user_id"),
  //   Goods(
  //       id: "id",
  //       imagePath: "assets/images/bag1.jpg",
  //       title: "가방 팝니다.",
  //       location: "청담동",
  //       time: "10분전",
  //       desc: "desc",
  //       price: "500,000원",
  //       likes: 1,
  //       like: true,
  //       category: "category",
  //       item_status: 0,
  //       user_id: "user_id"),
  //   Goods(
  //       id: "id",
  //       imagePath: "assets/images/bag2.png",
  //       title: "명품백 싸게 팔아요",
  //       location: "합정동",
  //       time: "9시간 전",
  //       desc: "desc",
  //       price: "2,500,000원",
  //       likes: 66,
  //       like: true,
  //       category: "category",
  //       item_status: 0,
  //       user_id: "user_id"),
  // ];

  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          '관심목록',
          style: TextStyle(
              color: Colors.black, fontSize: 20, fontWeight: FontWeight.w900),
        ),
        backgroundColor: Colors.white,
        foregroundColor: Colors.black,
      ),
      body: ListView.builder(
        itemCount: likes.length,
        itemBuilder: (context, index) {
          return Card(
            child: Container(
              padding: const EdgeInsets.symmetric(vertical: 10),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  ClipRRect(
                    borderRadius: BorderRadius.all(Radius.circular(5.0)),
                    child: Row(
                      children: [
                        Image.asset(
                          likes[index].imagePath[0],
                          height: 110,
                          width: 110,
                          fit: BoxFit.cover,
                        ),
                      ],
                    ),
                  ),
                  Expanded(
                      child: Container(
                          height: 100,
                          padding: const EdgeInsets.only(left: 20, top: 2),
                          alignment: Alignment.centerLeft,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                likes[index].title,
                                overflow: TextOverflow.ellipsis,
                                style: TextStyle(fontSize: 15),
                              ),
                              SizedBox(
                                height: 5,
                              ),
                              SizedBox(
                                height: 5,
                              ),
                              Text(
                                likes[index].price,
                                style: TextStyle(
                                    fontSize: 16,
                                    color: Colors.black,
                                    fontWeight: FontWeight.bold),
                              ),
                              Expanded(
                                child: Container(
                                  child: Row(
                                    mainAxisAlignment: MainAxisAlignment.end,
                                    crossAxisAlignment: CrossAxisAlignment.end,
                                    children: [
                                      Container(
                                        height: 18,
                                        child: Image.asset(
                                          likes[index].like
                                              ? "assets/images/cart-fill.png"
                                              : "assets/images/cart-empty.png",
                                          width: 13,
                                          height: 13,
                                        ),
                                      ),
                                      SizedBox(
                                        width: 3,
                                      ),
                                      Text("${likes[index].likes}")
                                    ],
                                  ),
                                ),
                              ),
                            ],
                          ))),
                ],
              ),
            ),
          );
        },
      ),
    );
  }
}
