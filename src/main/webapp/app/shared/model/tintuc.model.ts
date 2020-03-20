export interface ITintuc {
  id?: number;
  tieuDe?: string;
  imageThumbnail?: string;
  thoiGianDang?: string;
  noiDungTomTat?: string;
  noiDungDayDu?: any;
  tacGia?: string;
}

export class Tintuc implements ITintuc {
  constructor(
    public id?: number,
    public tieuDe?: string,
    public imageThumbnail?: string,
    public thoiGianDang?: string,
    public noiDungTomTat?: string,
    public noiDungDayDu?: any,
    public tacGia?: string
  ) {}
}
