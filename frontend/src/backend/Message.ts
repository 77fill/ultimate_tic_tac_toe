export default class Message {
    constructor(
        public type: "coords",
        public metaX: number,
        public metaY: number,
        public x: number,
        public y: number
    ) {}
}