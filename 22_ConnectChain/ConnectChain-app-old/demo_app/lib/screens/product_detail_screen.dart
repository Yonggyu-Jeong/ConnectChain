// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';

import 'package:provider/provider.dart';

import '../dummy_data.dart';
import '../models/goods.dart';
import '../providers/goods/goods_list.dart';
import 'my_page/history_screen.dart';

class ProductDetailScreen extends StatefulWidget {
  static const routeName = '/product-detail';

  @override
  ProductDetailScreenState createState() => ProductDetailScreenState();
}

class ProductDetailScreenState extends State<ProductDetailScreen> {
  PreferredSizeWidget _appbarWidget() {
    return AppBar(
      backgroundColor: Colors.transparent,
      elevation: 0,
      leading: IconButton(
          onPressed: () => Navigator.of(context).pop(),
          icon: Icon(
            Icons.arrow_back,
            color: Colors.white,
          )),
      actions: [
        IconButton(
          onPressed: () {},
          icon: Icon(
            Icons.more_vert,
            color: Colors.white,
          ),
        ),
      ],
    );
  }

  Widget sliderWidget(List<String> imagePath) {
    return Container(
        child: Stack(
      children: [
        CarouselSlider(
          options: CarouselOptions(
            height: MediaQuery.of(context).size.width,
            initialPage: 0,
            enableInfiniteScroll: false,
            viewportFraction: 1.0,
            enlargeCenterPage: false,
          ),
          items: imagePath.map((img) {
            return Container(
              width: MediaQuery.of(context).size.width,
              height: MediaQuery.of(context).size.height,
              child: Image.asset(
                img,
                fit: BoxFit.cover,
              ),
            );
          }).toList(),
        ),
      ],
    ));
  }

  Widget sellerinfo(String userId) {
    final userInfo = DUMMY_USER.firstWhere((user) => user.id == userId);
    late String userLevel;
    if (userInfo.level == 1 || userInfo.level == 2)
      userLevel = "신뢰도 보통";
    else if (userInfo.level == 3 || userInfo.level == 4)
      userLevel = "신뢰도 좋음";
    else if (userInfo.level >= 5) userLevel = "신뢰도 짱짱";

    return Padding(
      padding: const EdgeInsets.all(15.0),
      child: Row(
        children: [
          ClipRRect(
              borderRadius: BorderRadius.circular(50),
              child: Container(
                width: 50,
                height: 50,
                child: Image.asset(userInfo.profileImage),
              )),
          SizedBox(width: 10),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                userInfo.name,
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
              ),
              Text(userLevel),
            ],
          ),
          Expanded(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                GestureDetector(
                  onTap: () => Navigator.of(context).pushNamed(
                    HistoryScreen.routename,
                  ),
                  child: Container(
                    padding: const EdgeInsets.symmetric(
                        horizontal: 20, vertical: 10),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(5),
                      color: Theme.of(context).primaryColor,
                    ),
                    child: Row(
                      children: [
                        const Text(
                          "거래 기록",
                          style: TextStyle(
                              color: Colors.white,
                              fontSize: 16,
                              fontWeight: FontWeight.bold),
                        ),
                        const SizedBox(
                          width: 10,
                        ),
                        Image(
                          image: AssetImage("assets/images/trade.png"),
                          width: 30,
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget contentdetail(Goods goods) {
    return Container(
      margin: const EdgeInsets.symmetric(horizontal: 15),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          SizedBox(height: 20),
          Text(
            goods.title,
            style: TextStyle(fontWeight: FontWeight.bold, fontSize: 20),
          ),
          SizedBox(height: 15),
          Text(
            "${goods.category} . ${goods.time}",
            style: TextStyle(color: Colors.grey, fontSize: 12),
          ),
          SizedBox(height: 15),
          Text(
            goods.desc,
            style: TextStyle(
              fontSize: 15,
              height: 1.5,
            ),
          ),
        ],
      ),
    );
  }

  Widget line() {
    return Container(
      margin: const EdgeInsets.symmetric(horizontal: 15),
      height: 1,
      color: Theme.of(context).dividerColor,
    );
  }

  Widget _bodyWidget(Goods selectedGoods) {
    return SingleChildScrollView(
      child: Column(
        children: [
          sliderWidget(selectedGoods.imagePath),
          sellerinfo(selectedGoods.user_id),
          line(),
          contentdetail(selectedGoods),
        ],
      ),
    );
  }

  Widget _bottomBarWidget(Goods goods) {
    return Padding(
      padding: const EdgeInsets.all(10.0),
      child: Container(
        padding: const EdgeInsets.only(top: 3),
        decoration: BoxDecoration(
          border: Border(
            top: BorderSide(
              width: 1,
              color: Theme.of(context).dividerColor,
            ),
          ),
        ),
        width: MediaQuery.of(context).size.width,
        height: 55,
        child: Row(
          children: [
            GestureDetector(
              onTap: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(
                    duration: Duration(seconds: 1),
                    content: Text(
                      goods.like ? "관심목록에 제거되었습니다." : "관심목록에서 추가되었습니다.",
                    ),
                  ),
                );
                context.read<GoodsList>().toggleGoods(goods.goods_id);
              },
              child: Image.asset(
                goods.like
                    ? 'assets/images/cart-fill.png'
                    : 'assets/images/cart-empty.png',
                width: 25,
                height: 25,
                color: Theme.of(context).primaryColor,
              ),
            ),
            Container(
              margin: const EdgeInsets.only(left: 15, right: 10),
              width: 1.5,
              height: 40,
              color: Theme.of(context).dividerColor,
            ),
            Column(
              children: [
                SizedBox(
                  height: 8.0,
                ),
                Text(
                  goods.price,
                  style: TextStyle(
                    fontSize: 25,
                    fontWeight: FontWeight.bold,
                  ),
                )
              ],
            ),
            Expanded(
                child: Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                Container(
                  padding:
                      const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(5),
                    color: Theme.of(context).focusColor,
                  ),
                  child: Text(
                    "채팅하기",
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 16,
                        fontWeight: FontWeight.bold),
                  ),
                ),
              ],
            ))
          ],
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final goodsId = ModalRoute.of(context)!.settings.arguments as String;
    final selectedGoods = context
        .watch<GoodsList>()
        .state
        .goods
        .firstWhere((goods) => goods.goods_id == goodsId);
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: _appbarWidget(),
      body: _bodyWidget(selectedGoods),
      bottomNavigationBar: _bottomBarWidget(selectedGoods),
    );
  }
}
