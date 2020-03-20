export interface INews {
  id?: number;
  tieuDe?: string;
  imageThumbnail?: string;
  thoiGianDang?: string;
  noiDungTomTat?: string;
  noiDungDayDu?: string;
  tacGia?: string;
}

export class News implements INews {
  constructor(
    public id?: number,
    public tieuDe?: string,
    public imageThumbnail?: string,
    public thoiGianDang?: string,
    public noiDungTomTat?: string,
    public noiDungDayDu?: string,
    public tacGia?: string
  ) {}
}
