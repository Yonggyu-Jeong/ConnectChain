import 'package:flutter/material.dart';

class SellListScreen extends StatelessWidget {
  static const routeName = '/sell-list';
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
            tabs: [
              Tab(
                text: '판매중',
              ),
              Tab(text: '거래완료'),
            ],
          ),
        ),
        body: TabBarView(
          children: <Widget>[
            buildSell(),
            buildFinish(),
          ],
        ),
      ),
    );
  }
}

Widget buildSell() {
  return Center(
    child: const Text('sell'),
  );
}

Widget buildFinish() {
  return Center(
    child: const Text('finish'),
  );
}
