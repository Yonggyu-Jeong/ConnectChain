import 'show_category_screen.dart';
import 'package:flutter/material.dart';

class ProductRegisterScreen extends StatelessWidget {
  static const routeName = '/product-register';
  const ProductRegisterScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: Text('거래 글 작성하기'),
        titleTextStyle: TextStyle(fontSize: 20, color: Colors.black),
        centerTitle: true,
        elevation: 0.0,
        leading: IconButton(
          icon: Icon(
            Icons.cancel,
            color: Colors.black,
          ),
          onPressed: () => Navigator.of(context).pop(),
        ),
        actions: [
          IconButton(
            //TODO 등록함수 작성 후 종료
            onPressed: () => Navigator.of(context).pop(),
            icon: Icon(
              Icons.done_outline,
              color: Colors.black,
            ),
          ),
        ],
      ),
      body: Padding(
        padding: EdgeInsets.all(8.0),
        child: Column(
          children: <Widget>[
            IconButton(
              icon: Icon(
                Icons.add_photo_alternate,
                color: Colors.grey,
                size: 50,
              ),
              alignment: Alignment.center,
              onPressed: () {},
            ),
            SizedBox(
              height: 10,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                Container(
                    padding:
                        const EdgeInsets.symmetric(horizontal: 5, vertical: 5),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(5),
                      color: Color(0xff5296D5),
                    ),
                    child: const Text(
                      "NFT 발행할 사진 선택",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 15,
                      ),
                    )),
                Container(
                  alignment: Alignment.centerRight,
                  padding:
                      const EdgeInsets.symmetric(horizontal: 5, vertical: 5),
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(5),
                    color: Color(0xff925CB1),
                  ),
                  child: const Text(
                    "보유한 NFT 불러오기",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 15,
                    ),
                  ),
                ),
              ],
            ),
            TextField(
              decoration: InputDecoration(labelText: '글 제목'),
            ),
            ListTile(
              title: Text('카테고리 선택'),
              trailing: const Icon(Icons.chevron_right),
              onTap: () {
                Navigator.of(context).push(
                  MaterialPageRoute(
                    builder: ((_) => ShowCategoryScreen()),
                  ),
                );
              },
            ),
            TextField(
              decoration: InputDecoration(labelText: '가격(원)'),
            ),
            TextField(
              maxLines: 10,
              decoration: InputDecoration(labelText: '게시글 내용'),
            )
          ],
        ),
      ),
    );
  }
}
