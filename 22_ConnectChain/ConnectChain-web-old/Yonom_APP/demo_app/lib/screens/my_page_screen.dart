import 'package:flutter/material.dart';
import '../screens/sell_list_screen.dart';

class MyPageScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: <Widget>[
        Container(
          color: Colors.white,
          padding: EdgeInsets.all(13),
          child: Column(
            children: <Widget>[
              Row(
                children: [
                  CircleAvatar(
                    radius: 30,
                    backgroundImage: AssetImage('images/profile1.jpg'),
                  ),
                  SizedBox(width: 15),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        "냠냠냠",
                        style: TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      SizedBox(height: 5),
                      Row(
                        children: [
                          Text(
                            "성수동1가",
                            style:
                                TextStyle(color: Colors.black38, fontSize: 12),
                          ),
                          Text(
                            "#7716595",
                            style:
                                TextStyle(color: Colors.black38, fontSize: 12),
                          ),
                        ],
                      ),
                    ],
                  ),
                  Spacer(),
                ],
              ),
              SizedBox(height: 10),
              SizedBox(height: 10),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: <Widget>[
                  Column(
                    children: [
                      Listener(
                        onPointerUp: (PointerUpEvent event) {
                          Navigator.of(context).pushNamed(
                            SellListScreen.routeName,
                          );
                        },
                        child: CircleAvatar(
                          radius: 28,
                          backgroundColor:
                              Theme.of(context).accentColor.withOpacity(0.2),
                          child: IconButton(
                            icon: Icon(Icons.receipt_sharp),
                            color: Theme.of(context).accentColor,
                            onPressed: () {},
                          ),
                        ),
                      ),
                      SizedBox(height: 10),
                      Text('판매내역'),
                      SizedBox(height: 10),
                    ],
                  ),
                  Column(
                    children: [
                      CircleAvatar(
                        radius: 28,
                        backgroundColor:
                            Theme.of(context).accentColor.withOpacity(0.2),
                        child: IconButton(
                          icon: Icon(Icons.shopping_bag),
                          color: Theme.of(context).accentColor,
                          onPressed: () {},
                        ),
                      ),
                      SizedBox(height: 10),
                      Text('구매내역'),
                      SizedBox(height: 10),
                    ],
                  ),
                  Column(
                    children: [
                      CircleAvatar(
                        radius: 28,
                        backgroundColor:
                            Theme.of(context).accentColor.withOpacity(0.2),
                        child: IconButton(
                          icon: Icon(Icons.favorite),
                          color: Theme.of(context).accentColor,
                          onPressed: () {},
                        ),
                      ),
                      SizedBox(height: 10),
                      Text('관심목록'),
                      SizedBox(height: 10),
                    ],
                  ),
                ],
              ),
            ],
          ),
        ),
        Padding(
          padding: EdgeInsets.only(bottom: 10),
        ),
        Padding(padding: EdgeInsets.only(bottom: 10)),
      ],
    );
  }
}

class _MenuRow extends StatelessWidget {
  final IconData? icon;
  final String text;

  _MenuRow(this.icon, this.text);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 45,
      padding: EdgeInsets.all(10),
      child: Row(children: [
        if (icon != null) Icon(icon),
        SizedBox(width: 10),
        icon != null
            ? Text(text)
            : Text(
                text,
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                ),
              ),
      ]),
    );
  }
}
