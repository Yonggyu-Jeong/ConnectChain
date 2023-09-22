import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../models/goods.dart';
import '../providers/goods/goods_list.dart';
import '../screens/product_detail_screen.dart';

class HomeScreen extends StatelessWidget {
  Widget buildGoodsContainer(BuildContext context, Goods goods) {
    return InkWell(
      onTap: () => Navigator.of(context).pushNamed(
        ProductDetailScreen.routeName,
        arguments: goods.goods_id,
      ),
      child: Container(
        height: 140,
        // decoration: BoxDecoration(
        //   border: Border(
        //     bottom: BorderSide(
        //       color: Colors.black12,
        //       // color: Theme.of(context).dividerColor,
        //     ),
        //   ),
        // ),
        child: Row(
          children: <Widget>[
            Container(
              margin: EdgeInsets.all(15),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.all(Radius.circular(10.0)),
              ),
              child: ClipRRect(
                child: Image.asset(
                  goods.imagePath[0],
                  height: 110,
                  width: 110,
                  fit: BoxFit.cover,
                ),
                borderRadius: BorderRadius.all(Radius.circular(10)),
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
                        goods.like ? Icons.favorite : Icons.favorite_border,
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

  @override
  Widget build(BuildContext context) {
    final goods = context.watch<GoodsList>().state.goods;
    return Container(
      child: ListView.builder(
        itemCount: goods.length,
        itemBuilder: (BuildContext context, int i) {
          return buildGoodsContainer(context, goods[i]);
        },
      ),
    );
  }
}
