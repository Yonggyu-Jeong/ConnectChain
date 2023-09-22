import 'dart:convert';

import 'package:demo_app/models/goods.dart';
import 'package:http/http.dart' as http;

class GoodsApi {
  final http.Client _client;

  GoodsApi({http.Client? client}) : _client = (client ?? http.Client());
  static const baseUrl = 'http://49.50.161.216';

  Future<List<Goods>> getGoodsList() async {
    final response = await _client.get(Uri.parse('$baseUrl/goods/goods-list'));
    print(response.body);

    final Iterable json = jsonDecode(response.body);
    return json.map((e) => Goods.fromJson(e)).toList();
  }
}
