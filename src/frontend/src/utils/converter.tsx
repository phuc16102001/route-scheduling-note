const converter = {
    instantToDateTime(instant: number) {
        return new Date(instant * 1000);
    }
}

export default converter;