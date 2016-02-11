// automatically generated by the FlatBuffers compiler, do not modify

/**
 * @const
*/
var com = com || {};

/**
 * @const
*/
com.clue = com.clue || {};

/**
 * @const
*/
com.clue.fbs = com.clue.fbs || {};

/**
 * @const
*/
com.clue.fbs.RemoteLogger = com.clue.fbs.RemoteLogger || {};

/**
 * @enum
 */
com.clue.fbs.RemoteLogger.MessageType = {
  ReqJoin: 0,
  ReqLog: 1,
  NotiLog: 2,
  ReqRoomList: 3,
  ResRoomList: 4
};

/**
 * @constructor
 */
com.clue.fbs.RemoteLogger.ReqJoin = function() {
  /**
   * @type {flatbuffers.ByteBuffer}
   */
  this.bb = null;

  /**
   * @type {number}
   */
  this.bb_pos = 0;
};

/**
 * @param {number} i
 * @param {flatbuffers.ByteBuffer} bb
 * @returns {com.clue.fbs.RemoteLogger.ReqJoin}
 */
com.clue.fbs.RemoteLogger.ReqJoin.prototype.__init = function(i, bb) {
  this.bb_pos = i;
  this.bb = bb;
  return this;
};

/**
 * @param {flatbuffers.ByteBuffer} bb
 * @param {com.clue.fbs.RemoteLogger.ReqJoin=} obj
 * @returns {com.clue.fbs.RemoteLogger.ReqJoin}
 */
com.clue.fbs.RemoteLogger.ReqJoin.getRootAsReqJoin = function(bb, obj) {
  return (obj || new com.clue.fbs.RemoteLogger.ReqJoin).__init(bb.readInt32(bb.position()) + bb.position(), bb);
};

/**
 * @param {flatbuffers.Encoding=} optionalEncoding
 * @returns {string|Uint8Array}
 */
com.clue.fbs.RemoteLogger.ReqJoin.prototype.roomId = function(optionalEncoding) {
  var offset = this.bb.__offset(this.bb_pos, 4);
  return offset ? this.bb.__string(this.bb_pos + offset, optionalEncoding) : null;
};

/**
 * @param {flatbuffers.Builder} builder
 */
com.clue.fbs.RemoteLogger.ReqJoin.startReqJoin = function(builder) {
  builder.startObject(1);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {flatbuffers.Offset} roomIdOffset
 */
com.clue.fbs.RemoteLogger.ReqJoin.addRoomId = function(builder, roomIdOffset) {
  builder.addFieldOffset(0, roomIdOffset, 0);
};

/**
 * @param {flatbuffers.Builder} builder
 * @returns {flatbuffers.Offset}
 */
com.clue.fbs.RemoteLogger.ReqJoin.endReqJoin = function(builder) {
  var offset = builder.endObject();
  return offset;
};

/**
 * @constructor
 */
com.clue.fbs.RemoteLogger.ReqLog = function() {
  /**
   * @type {flatbuffers.ByteBuffer}
   */
  this.bb = null;

  /**
   * @type {number}
   */
  this.bb_pos = 0;
};

/**
 * @param {number} i
 * @param {flatbuffers.ByteBuffer} bb
 * @returns {com.clue.fbs.RemoteLogger.ReqLog}
 */
com.clue.fbs.RemoteLogger.ReqLog.prototype.__init = function(i, bb) {
  this.bb_pos = i;
  this.bb = bb;
  return this;
};

/**
 * @param {flatbuffers.ByteBuffer} bb
 * @param {com.clue.fbs.RemoteLogger.ReqLog=} obj
 * @returns {com.clue.fbs.RemoteLogger.ReqLog}
 */
com.clue.fbs.RemoteLogger.ReqLog.getRootAsReqLog = function(bb, obj) {
  return (obj || new com.clue.fbs.RemoteLogger.ReqLog).__init(bb.readInt32(bb.position()) + bb.position(), bb);
};

/**
 * @param {flatbuffers.Encoding=} optionalEncoding
 * @returns {string|Uint8Array}
 */
com.clue.fbs.RemoteLogger.ReqLog.prototype.message = function(optionalEncoding) {
  var offset = this.bb.__offset(this.bb_pos, 4);
  return offset ? this.bb.__string(this.bb_pos + offset, optionalEncoding) : null;
};

/**
 * @returns {number}
 */
com.clue.fbs.RemoteLogger.ReqLog.prototype.level = function() {
  var offset = this.bb.__offset(this.bb_pos, 6);
  return offset ? this.bb.readInt8(this.bb_pos + offset) : 0;
};

/**
 * @param {flatbuffers.Builder} builder
 */
com.clue.fbs.RemoteLogger.ReqLog.startReqLog = function(builder) {
  builder.startObject(2);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {flatbuffers.Offset} messageOffset
 */
com.clue.fbs.RemoteLogger.ReqLog.addMessage = function(builder, messageOffset) {
  builder.addFieldOffset(0, messageOffset, 0);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {number} level
 */
com.clue.fbs.RemoteLogger.ReqLog.addLevel = function(builder, level) {
  builder.addFieldInt8(1, level, 0);
};

/**
 * @param {flatbuffers.Builder} builder
 * @returns {flatbuffers.Offset}
 */
com.clue.fbs.RemoteLogger.ReqLog.endReqLog = function(builder) {
  var offset = builder.endObject();
  return offset;
};

/**
 * @constructor
 */
com.clue.fbs.RemoteLogger.NotiLog = function() {
  /**
   * @type {flatbuffers.ByteBuffer}
   */
  this.bb = null;

  /**
   * @type {number}
   */
  this.bb_pos = 0;
};

/**
 * @param {number} i
 * @param {flatbuffers.ByteBuffer} bb
 * @returns {com.clue.fbs.RemoteLogger.NotiLog}
 */
com.clue.fbs.RemoteLogger.NotiLog.prototype.__init = function(i, bb) {
  this.bb_pos = i;
  this.bb = bb;
  return this;
};

/**
 * @param {flatbuffers.ByteBuffer} bb
 * @param {com.clue.fbs.RemoteLogger.NotiLog=} obj
 * @returns {com.clue.fbs.RemoteLogger.NotiLog}
 */
com.clue.fbs.RemoteLogger.NotiLog.getRootAsNotiLog = function(bb, obj) {
  return (obj || new com.clue.fbs.RemoteLogger.NotiLog).__init(bb.readInt32(bb.position()) + bb.position(), bb);
};

/**
 * @param {flatbuffers.Encoding=} optionalEncoding
 * @returns {string|Uint8Array}
 */
com.clue.fbs.RemoteLogger.NotiLog.prototype.message = function(optionalEncoding) {
  var offset = this.bb.__offset(this.bb_pos, 4);
  return offset ? this.bb.__string(this.bb_pos + offset, optionalEncoding) : null;
};

/**
 * @returns {number}
 */
com.clue.fbs.RemoteLogger.NotiLog.prototype.level = function() {
  var offset = this.bb.__offset(this.bb_pos, 6);
  return offset ? this.bb.readInt8(this.bb_pos + offset) : 0;
};

/**
 * @param {flatbuffers.Builder} builder
 */
com.clue.fbs.RemoteLogger.NotiLog.startNotiLog = function(builder) {
  builder.startObject(2);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {flatbuffers.Offset} messageOffset
 */
com.clue.fbs.RemoteLogger.NotiLog.addMessage = function(builder, messageOffset) {
  builder.addFieldOffset(0, messageOffset, 0);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {number} level
 */
com.clue.fbs.RemoteLogger.NotiLog.addLevel = function(builder, level) {
  builder.addFieldInt8(1, level, 0);
};

/**
 * @param {flatbuffers.Builder} builder
 * @returns {flatbuffers.Offset}
 */
com.clue.fbs.RemoteLogger.NotiLog.endNotiLog = function(builder) {
  var offset = builder.endObject();
  return offset;
};

/**
 * @constructor
 */
com.clue.fbs.RemoteLogger.ReqRoomList = function() {
  /**
   * @type {flatbuffers.ByteBuffer}
   */
  this.bb = null;

  /**
   * @type {number}
   */
  this.bb_pos = 0;
};

/**
 * @param {number} i
 * @param {flatbuffers.ByteBuffer} bb
 * @returns {com.clue.fbs.RemoteLogger.ReqRoomList}
 */
com.clue.fbs.RemoteLogger.ReqRoomList.prototype.__init = function(i, bb) {
  this.bb_pos = i;
  this.bb = bb;
  return this;
};

/**
 * @param {flatbuffers.ByteBuffer} bb
 * @param {com.clue.fbs.RemoteLogger.ReqRoomList=} obj
 * @returns {com.clue.fbs.RemoteLogger.ReqRoomList}
 */
com.clue.fbs.RemoteLogger.ReqRoomList.getRootAsReqRoomList = function(bb, obj) {
  return (obj || new com.clue.fbs.RemoteLogger.ReqRoomList).__init(bb.readInt32(bb.position()) + bb.position(), bb);
};

/**
 * @returns {number}
 */
com.clue.fbs.RemoteLogger.ReqRoomList.prototype.pageCount = function() {
  var offset = this.bb.__offset(this.bb_pos, 4);
  return offset ? this.bb.readInt32(this.bb_pos + offset) : 0;
};

/**
 * @param {flatbuffers.Builder} builder
 */
com.clue.fbs.RemoteLogger.ReqRoomList.startReqRoomList = function(builder) {
  builder.startObject(1);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {number} pageCount
 */
com.clue.fbs.RemoteLogger.ReqRoomList.addPageCount = function(builder, pageCount) {
  builder.addFieldInt32(0, pageCount, 0);
};

/**
 * @param {flatbuffers.Builder} builder
 * @returns {flatbuffers.Offset}
 */
com.clue.fbs.RemoteLogger.ReqRoomList.endReqRoomList = function(builder) {
  var offset = builder.endObject();
  return offset;
};

/**
 * @constructor
 */
com.clue.fbs.RemoteLogger.ResRoomList = function() {
  /**
   * @type {flatbuffers.ByteBuffer}
   */
  this.bb = null;

  /**
   * @type {number}
   */
  this.bb_pos = 0;
};

/**
 * @param {number} i
 * @param {flatbuffers.ByteBuffer} bb
 * @returns {com.clue.fbs.RemoteLogger.ResRoomList}
 */
com.clue.fbs.RemoteLogger.ResRoomList.prototype.__init = function(i, bb) {
  this.bb_pos = i;
  this.bb = bb;
  return this;
};

/**
 * @param {flatbuffers.ByteBuffer} bb
 * @param {com.clue.fbs.RemoteLogger.ResRoomList=} obj
 * @returns {com.clue.fbs.RemoteLogger.ResRoomList}
 */
com.clue.fbs.RemoteLogger.ResRoomList.getRootAsResRoomList = function(bb, obj) {
  return (obj || new com.clue.fbs.RemoteLogger.ResRoomList).__init(bb.readInt32(bb.position()) + bb.position(), bb);
};

/**
 * @param {number} index
 * @param {com.clue.fbs.RemoteLogger.RoomInfo=} obj
 * @returns {com.clue.fbs.RemoteLogger.RoomInfo}
 */
com.clue.fbs.RemoteLogger.ResRoomList.prototype.rooms = function(index, obj) {
  var offset = this.bb.__offset(this.bb_pos, 4);
  return offset ? (obj || new com.clue.fbs.RemoteLogger.RoomInfo).__init(this.bb.__indirect(this.bb.__vector(this.bb_pos + offset) + index * 4), this.bb) : null;
};

/**
 * @returns {number}
 */
com.clue.fbs.RemoteLogger.ResRoomList.prototype.roomsLength = function() {
  var offset = this.bb.__offset(this.bb_pos, 4);
  return offset ? this.bb.__vector_len(this.bb_pos + offset) : 0;
};

/**
 * @param {flatbuffers.Builder} builder
 */
com.clue.fbs.RemoteLogger.ResRoomList.startResRoomList = function(builder) {
  builder.startObject(1);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {flatbuffers.Offset} roomsOffset
 */
com.clue.fbs.RemoteLogger.ResRoomList.addRooms = function(builder, roomsOffset) {
  builder.addFieldOffset(0, roomsOffset, 0);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {Array.<flatbuffers.Offset>} data
 * @returns {flatbuffers.Offset}
 */
com.clue.fbs.RemoteLogger.ResRoomList.createRoomsVector = function(builder, data) {
  builder.startVector(4, data.length, 4);
  for (var i = data.length - 1; i >= 0; i--) {
    builder.addOffset(data[i]);
  }
  return builder.endVector();
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {number} numElems
 */
com.clue.fbs.RemoteLogger.ResRoomList.startRoomsVector = function(builder, numElems) {
  builder.startVector(4, numElems, 4);
};

/**
 * @param {flatbuffers.Builder} builder
 * @returns {flatbuffers.Offset}
 */
com.clue.fbs.RemoteLogger.ResRoomList.endResRoomList = function(builder) {
  var offset = builder.endObject();
  return offset;
};

/**
 * @constructor
 */
com.clue.fbs.RemoteLogger.RoomInfo = function() {
  /**
   * @type {flatbuffers.ByteBuffer}
   */
  this.bb = null;

  /**
   * @type {number}
   */
  this.bb_pos = 0;
};

/**
 * @param {number} i
 * @param {flatbuffers.ByteBuffer} bb
 * @returns {com.clue.fbs.RemoteLogger.RoomInfo}
 */
com.clue.fbs.RemoteLogger.RoomInfo.prototype.__init = function(i, bb) {
  this.bb_pos = i;
  this.bb = bb;
  return this;
};

/**
 * @param {flatbuffers.ByteBuffer} bb
 * @param {com.clue.fbs.RemoteLogger.RoomInfo=} obj
 * @returns {com.clue.fbs.RemoteLogger.RoomInfo}
 */
com.clue.fbs.RemoteLogger.RoomInfo.getRootAsRoomInfo = function(bb, obj) {
  return (obj || new com.clue.fbs.RemoteLogger.RoomInfo).__init(bb.readInt32(bb.position()) + bb.position(), bb);
};

/**
 * @param {flatbuffers.Encoding=} optionalEncoding
 * @returns {string|Uint8Array}
 */
com.clue.fbs.RemoteLogger.RoomInfo.prototype.roomId = function(optionalEncoding) {
  var offset = this.bb.__offset(this.bb_pos, 4);
  return offset ? this.bb.__string(this.bb_pos + offset, optionalEncoding) : null;
};

/**
 * @returns {number}
 */
com.clue.fbs.RemoteLogger.RoomInfo.prototype.count = function() {
  var offset = this.bb.__offset(this.bb_pos, 6);
  return offset ? this.bb.readInt32(this.bb_pos + offset) : 0;
};

/**
 * @param {flatbuffers.Builder} builder
 */
com.clue.fbs.RemoteLogger.RoomInfo.startRoomInfo = function(builder) {
  builder.startObject(2);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {flatbuffers.Offset} roomIdOffset
 */
com.clue.fbs.RemoteLogger.RoomInfo.addRoomId = function(builder, roomIdOffset) {
  builder.addFieldOffset(0, roomIdOffset, 0);
};

/**
 * @param {flatbuffers.Builder} builder
 * @param {number} count
 */
com.clue.fbs.RemoteLogger.RoomInfo.addCount = function(builder, count) {
  builder.addFieldInt32(1, count, 0);
};

/**
 * @param {flatbuffers.Builder} builder
 * @returns {flatbuffers.Offset}
 */
com.clue.fbs.RemoteLogger.RoomInfo.endRoomInfo = function(builder) {
  var offset = builder.endObject();
  return offset;
};

// Exports for Node.js and RequireJS
this.com = com;
