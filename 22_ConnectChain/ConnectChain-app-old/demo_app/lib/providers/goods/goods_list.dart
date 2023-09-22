// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';

import '../../dummy_data.dart';
import '../../models/goods.dart';

class GoodsListState extends Equatable {
  final List<Goods> goods;
  GoodsListState({
    required this.goods,
  });

  factory GoodsListState.initial() {
    return GoodsListState(goods: DUMMY_GOODS);
  }

  @override
  List<Object> get props => [goods];

  @override
  bool get stringify => true;

  GoodsListState copyWith({
    List<Goods>? goods,
  }) {
    return GoodsListState(
      goods: goods ?? this.goods,
    );
  }
}

class GoodsList with ChangeNotifier {
  GoodsListState _state = GoodsListState.initial();
  GoodsListState get state => _state;

  void toggleGoods(String goods_id) {
    final newGoods = _state.goods.map((Goods goods) {
      if (goods.goods_id == goods_id) {
        return Goods(
          goods_id: goods_id,
          imagePath: goods.imagePath,
          time: goods.time,
          title: goods.title,
          desc: goods.desc,
          price: goods.price,
          likes: goods.likes,
          like: !goods.like,
          category: goods.category,
          item_status: goods.item_status,
          user_id: goods.user_id,
        );
      }
      return goods;
    }).toList();

    _state = _state.copyWith(goods: newGoods);
    notifyListeners();
  }
}
