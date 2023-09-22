import '../../dummy_data.dart';
import 'package:flutter/material.dart';
import '../product_detail_screen.dart';

import '../../models/goods.dart';

class SellListScreen extends StatelessWidget {
  static const routeName = '/sell-list';
  final sellitems = DUMMY_GOODS;

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 2,
      child: Scaffold(
        appBar: AppBar(
          centerTitle: false,
          backgroundColor: Colors.white,
          foregroundColor: Colors.black,
          elevation: 0,
          shape: Border(
            bottom: BorderSide(
              color: Colors.black26,
            ),
          ),
          title: Text(
            '판매내역',
            style: TextStyle(fontSize: 20, fontWeight: FontWeight.w900),
          ),
          bottom: TabBar(
            labelColor: Colors.black,
            unselectedLabelColor: Colors.black26,
            labelStyle: TextStyle(fontWeight: FontWeight.w900),
            indicatorColor: Theme.of(context).primaryColor,
            tabs: [
              Tab(
                text: '판매중',
              ),
              Tab(
                text: '거래완료',
              ),
            ],
          ),
        ),
        body: TabBarView(
          children: <Widget>[
            buildSell(context),
            buildFinish(context),
          ],
        ),
      ),
    );
  }

  Widget buildBuyItemContainer(BuildContext context, Goods goods) {
    return GestureDetector(
      onTap: () => Navigator.of(context).pushNamed(
        ProductDetailScreen.routeName,
      ),
      child: Container(
        height: 140,
        decoration: BoxDecoration(
          border: Border(
            bottom: BorderSide(
              color: Colors.black12,
            ),
          ),
        ),
        child: Row(
          children: <Widget>[
            Container(
              margin: EdgeInsets.all(15),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.all(Radius.circular(10.0)),
              ),
              child: Image.asset(
                goods.imagePath[0],
                height: 110,
                width: 110,
                fit: BoxFit.cover,
              ),
            ),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  SizedBox(height: 18),
                  Text(
                    goods.title,
                    style: TextStyle(fontSize: 15),
                  ),
                  Row(
                    children: [
                      Text(
                        goods.time,
                        style: TextStyle(color: Colors.black38, fontSize: 12),
                      ),
                    ],
                  ),
                  Text(
                    goods.price,
                    style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 25),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: <Widget>[
                      Icon(
                        Icons.favorite_border,
                        color: Colors.black26,
                        size: 20,
                      ),
                      SizedBox(width: 2),
                      Text(goods.likes.toString()),
                      SizedBox(width: 10),
                    ],
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget buildSell(BuildContext context) {
    //판매중
    return SingleChildScrollView(
      child: Column(children: [
        buildBuyItemContainer(context, sellitems[0]),
        buildBuyItemContainer(context, sellitems[1]),
        buildBuyItemContainer(context, sellitems[0]),
        buildBuyItemContainer(context, sellitems[1]),
      ]),
    );
  }

  Widget buildFinish(BuildContext context) {
    //거래완료
    return SingleChildScrollView(
      child: Column(
        children: [
          buildBuyItemContainer(context, sellitems[0]),
          buildBuyItemContainer(context, sellitems[0]),
          buildBuyItemContainer(context, sellitems[0]),
          buildBuyItemContainer(context, sellitems[0]),
        ],
      ),
    );
  }
/*
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('관심목록'),
      ),
      body: ListView.builder(
        itemCount: sellitems.length,
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
                          sellitems[index].imagePath,
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
                                sellitems[index].title,
                                overflow: TextOverflow.ellipsis,
                                style: TextStyle(fontSize: 15),
                              ),
                              SizedBox(
                                height: 5,
                              ),
                              Text(
                                sellitems[index].location,
                                style: TextStyle(
                                    fontSize: 12, color: Colors.black38),
                              ),
                              SizedBox(
                                height: 5,
                              ),
                              Text(
                                sellitems[index].price,
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
                                          sellitems[index].like
                                              ? "assets/images/cart-fill.png"
                                              : "assets/images/cart-empty.png",
                                          width: 13,
                                          height: 13,
                                        ),
                                      ),
                                      SizedBox(
                                        width: 3,
                                      ),
                                      Text("${sellitems[index].likes}")
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
  */
}
