// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'goods.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_Goods _$$_GoodsFromJson(Map<String, dynamic> json) => _$_Goods(
      goods_id: json['goods_id'] as String,
      imagePath:
          (json['imagePath'] as List<dynamic>).map((e) => e as String).toList(),
      time: json['time'] as String,
      title: json['title'] as String,
      desc: json['desc'] as String,
      price: json['price'] as String,
      likes: json['likes'] as int,
      like: json['like'] as bool,
      category: json['category'] as String,
      item_status: json['item_status'] as int,
      user_id: json['user_id'] as String,
    );

Map<String, dynamic> _$$_GoodsToJson(_$_Goods instance) => <String, dynamic>{
      'goods_id': instance.goods_id,
      'imagePath': instance.imagePath,
      'time': instance.time,
      'title': instance.title,
      'desc': instance.desc,
      'price': instance.price,
      'likes': instance.likes,
      'like': instance.like,
      'category': instance.category,
      'item_status': instance.item_status,
      'user_id': instance.user_id,
    };
