import 'package:flutter/material.dart';

import '../models/home_item.dart';
import '../dummy_data.dart';

class HomeScreen extends StatelessWidget {
  final _homeItems = DUMMY_HOMEITEMS;

  Widget buildHomeItemContainer(HomeItem item) {
    return Container(
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
              item.imagePath,
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
                  item.title,
                  style: TextStyle(fontSize: 15),
                ),
                Row(
                  children: [
                    Text(
                      item.location,
                      style: TextStyle(color: Colors.black38, fontSize: 12),
                    ),
                    Text(
                      ' · ',
                      style: TextStyle(color: Colors.black38, fontSize: 12),
                    ),
                    Text(
                      item.time,
                      style: TextStyle(color: Colors.black38, fontSize: 12),
                    ),
                  ],
                ),
                Text(
                  item.price,
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
                    Text(item.likes.toString()),
                    SizedBox(width: 10),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: [
          buildHomeItemContainer(_homeItems[0]),
          buildHomeItemContainer(_homeItems[1]),
          buildHomeItemContainer(_homeItems[0]),
          buildHomeItemContainer(_homeItems[1]),
          buildHomeItemContainer(_homeItems[0]),
          buildHomeItemContainer(_homeItems[1]),
          buildHomeItemContainer(_homeItems[0]),
          buildHomeItemContainer(_homeItems[1]),
          buildHomeItemContainer(_homeItems[0]),
          buildHomeItemContainer(_homeItems[1]),
          buildHomeItemContainer(_homeItems[0]),
          buildHomeItemContainer(_homeItems[1]),
          buildHomeItemContainer(_homeItems[0]),
        ],
      ),
    );
  }
}
