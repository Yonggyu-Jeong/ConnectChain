import 'package:flutter/material.dart';
import 'package:flutter/gestures.dart';

class StartScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Expanded(
            flex: 8,
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Image(
                    width: 100,
                    image: AssetImage('images/436px-DaangnMarket_logo.png'),
                  ),
                  SizedBox(height: 15),
                  Text(
                    '당신 근처의 당근마켓',
                    style: TextStyle(
                      fontSize: 19,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  SizedBox(height: 5),
                  Text('중고 거래부터 동네 정보까지,'),
                  Text('지금 내 동네를 선택하고 시작해보세요!'),
                ],
              ),
            ),
          ),
          Expanded(
            flex: 1,
            child: Container(
              width: double.infinity,
              padding: EdgeInsets.only(left: 15, right: 15),
              margin: EdgeInsets.only(bottom: 25),
              decoration:
                  BoxDecoration(borderRadius: BorderRadius.circular(10)),
              child: RaisedButton(
                color: Color.fromRGBO(254, 126, 53, 1.0),
                textColor: Colors.white,
                child: Text(
                  '시작하기',
                  style: TextStyle(
                    fontSize: 17,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                onPressed: () {
                  Navigator.pushNamed(context, '/singIn');
                },
              ),
            ),
          ),
          Expanded(
            flex: 1,
            child: RichText(
              text: TextSpan(
                children: [
                  TextSpan(
                    text: '이미 계정이 있나요? ',
                    style: TextStyle(
                      color: Colors.black38,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  TextSpan(
                    text: '로그인',
                    recognizer: new TapGestureRecognizer()
                      ..onTap = () => Navigator.pushNamed(context, '/logIn'),
                    style: TextStyle(
                      color: Color.fromRGBO(254, 126, 53, 1.0),
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
