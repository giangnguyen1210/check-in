export interface AuthResponse{
    accessToken: string;
    data: string;
    errorCode: string;
    errorDesc: string;
    tokenType: string;
    totalRecords: number;
}

export interface BaseResponse{
    data: string;
    errorCode: string;
    errorDesc: string;
    totalRecords: number;
}

export interface Option {
    optionText: string;
  }
  