// https://github.com/PlexPt/chatgpt-java/tree/2ebd898240b082b65f41f86bfa11a282ecf7d7a2/src/main/java/com/plexpt/chatgpt/ChatGPT.java#L210-L221
public class TempClass {
        RequestBody m = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), mask);
        MultipartBody.Part mPart = MultipartBody.Part.createFormData("mask", mask.getName(), m);

        Single<ImagesRensponse> imagesRensponse =
                this.apiClient.imageEdits(iPart, mPart, edits);
        return imagesRensponse.blockingGet();
    }

    public ImagesRensponse imageVariation(File image, Variations variations) {
        RequestBody i = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), image);
        MultipartBody.Part iPart = MultipartBody.Part.createFormData("image", image.getName(), i);
        Single<ImagesRensponse> imagesRensponse =

}