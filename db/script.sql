

USE [QuanLyCafe]
GO
/****** Object:  Table [dbo].[Ban]    Script Date: 11/9/2022 6:54:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ban](
	[maBan] [nvarchar](50) NOT NULL,
	[tenBan] [nvarchar](50) NOT NULL,
	[trangThai] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Ban] PRIMARY KEY CLUSTERED 
(
	[maBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTHoaDon]    Script Date: 11/9/2022 6:54:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHoaDon](
	[maSP] [nvarchar](50) NOT NULL,
	[maHD] [nvarchar](50) NOT NULL,
	[soLuong] [int] NOT NULL,
 CONSTRAINT [PK_CTHoaDon] PRIMARY KEY CLUSTERED 
(
	[maSP] ASC,
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 11/9/2022 6:54:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [nvarchar](50) NOT NULL,
	[ngayLapHD] [date] NOT NULL,
	[trangThai] [nvarchar](50) NOT NULL,
	[maNV] [nvarchar](50) NOT NULL,
	[maBan] [nvarchar](50) NOT NULL,
	[maKH] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 11/9/2022 6:54:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [nvarchar](50) NOT NULL,
	[tenKH] [nvarchar](50) NOT NULL,
	[sdt] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[gioiTinh] [nvarchar](50) NULL,
	[diaChi] [nvarchar](50) NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/9/2022 6:54:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [nvarchar](50) NOT NULL,
	[tenNV] [nvarchar](50) NOT NULL,
	[sdt] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[gioiTinh] [nvarchar](50) NOT NULL,
	[cmnd] [nvarchar](50) NOT NULL,
	[caTruc] [int] NOT NULL,
	[diaChi] [nvarchar](50) NOT NULL,
	[luong] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/9/2022 6:54:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSP] [nvarchar](50) NOT NULL,
	[tenSP] [nvarchar](50) NOT NULL,
	[gia] [decimal](18, 2) NOT NULL,
	[congThuc] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 11/9/2022 6:54:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[userName] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[maNV] [nvarchar](50) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B01', N'B01', N'active')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B02', N'B02', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B03', N'B03', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B04', N'B04', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B05', N'B05', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B06', N'B06', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B07', N'B07', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B08', N'B08', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B09', N'B09', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B10', N'B10', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B11', N'B11', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B12', N'B12', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B13', N'B13', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B14', N'B14', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B15', N'B15', N'trong')
INSERT [dbo].[Ban] ([maBan], [tenBan], [trangThai]) VALUES (N'B16', N'B16', N'trong')
GO
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP01', N'20221102-0737-KH01B08', 6)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP01', N'202211060009KH01B08', 1)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP01', N'202211060012KH01B07', 19)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP01', N'202211060017KH01B04', 4)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP01', N'202211060022KH01B08', 14)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP01', N'202211060024KH01B07', 6)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP01', N'202211060026KH01B01', 5)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP01', N'202211060210KH01B01', 1)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP02', N'20221102-0737-KH01B08', 5)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP03', N'20221102-0737-KH01B08', 4)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP03', N'202211060017KH01B07', 3)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP102', N'202211080100KH01B01', 2)
INSERT [dbo].[CTHoaDon] ([maSP], [maHD], [soLuong]) VALUES (N'SP395', N'202211080100KH01B01', 1)
GO
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'20221102-0737-KH01B08', CAST(N'2022-11-02' AS Date), N'true', N'NV001', N'B08', N'KH01')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'202211060009KH01B08', CAST(N'2022-11-06' AS Date), N'true', N'NV001', N'B08', N'KH01')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'202211060012KH01B07', CAST(N'2022-11-06' AS Date), N'true', N'NV001', N'B07', N'KH01')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'202211060017KH01B04', CAST(N'2022-11-06' AS Date), N'true', N'NV001', N'B04', N'KH01')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'202211060017KH01B07', CAST(N'2022-11-06' AS Date), N'true', N'NV001', N'B07', N'KH01')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'202211060022KH01B08', CAST(N'2022-11-06' AS Date), N'true', N'NV001', N'B08', N'KH01')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'202211060024KH01B07', CAST(N'2022-11-06' AS Date), N'true', N'NV001', N'B07', N'KH01')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'202211060026KH01B01', CAST(N'2022-11-06' AS Date), N'true', N'NV001', N'B01', N'KH01')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'202211060210KH01B01', CAST(N'2022-11-06' AS Date), N'true', N'NV001', N'B01', N'KH01')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [trangThai], [maNV], [maBan], [maKH]) VALUES (N'202211080100KH01B01', CAST(N'2022-11-08' AS Date), N'false', N'NV001', N'B01', N'KH01')
GO
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [sdt], [email], [gioiTinh], [diaChi]) VALUES (N'KH01', N'Le Quoc Phong', N'032514878', N'phong@gmail.com', N'Nam', N'GV')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [sdt], [email], [gioiTinh], [diaChi]) VALUES (N'KH02', N'Pham Ha Nam', N'032514878', N'phong@gmail.com', N'Nam', N'GV')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [sdt], [email], [gioiTinh], [diaChi]) VALUES (N'KH03', N'Le Dinh CHuong', N'032514878', N'phong@gmail.com', N'Nam', N'GV')
GO
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [sdt], [email], [gioiTinh], [cmnd], [caTruc], [diaChi], [luong]) VALUES (N'NV001', N'Pham Ha Nam', N'123456789', N'nam@gmail.com', N'nam', N'123456789', 2, N'aaa', CAST(5000000.00 AS Decimal(18, 2)))
GO
INSERT [dbo].[SanPham] ([maSP], [tenSP], [gia], [congThuc]) VALUES (N'SP01', N'Cafe đen', CAST(25000.00 AS Decimal(18, 2)), N'100ml cafe den 100ml sua...')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [gia], [congThuc]) VALUES (N'SP02', N'Cafe sửa', CAST(30000.00 AS Decimal(18, 2)), N'100ml cafe den 100ml sua...')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [gia], [congThuc]) VALUES (N'SP03', N'Cafe Machito', CAST(35000.00 AS Decimal(18, 2)), N'100ml cafe den 100ml sua...')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [gia], [congThuc]) VALUES (N'SP04', N'Cafe Cherry', CAST(35000.00 AS Decimal(18, 2)), N'100ml cafe den 100ml sua...')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [gia], [congThuc]) VALUES (N'SP102', N'Sinh tố dâu', CAST(25000.00 AS Decimal(18, 2)), N'	100g dâu tươi, 50ml sửa,hương dâu,...')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [gia], [congThuc]) VALUES (N'SP225', N'Trà đào', CAST(20000.00 AS Decimal(18, 2)), N'2 lát đào tươi, 100ml trà đào, 10g đường...')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [gia], [congThuc]) VALUES (N'SP395', N'Bạc sỉu', CAST(17000.00 AS Decimal(18, 2)), N'50ml cafe, 50ml sửa,50ml nước tinh thiết,...')
GO
INSERT [dbo].[TaiKhoan] ([userName], [password], [maNV]) VALUES (N'NV001', N'1', NULL)
GO
ALTER TABLE [dbo].[CTHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_CTHoaDon_HoaDon] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon] ([maHD])
GO
ALTER TABLE [dbo].[CTHoaDon] CHECK CONSTRAINT [FK_CTHoaDon_HoaDon]
GO
ALTER TABLE [dbo].[CTHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_CTHoaDon_SanPham] FOREIGN KEY([maSP])
REFERENCES [dbo].[SanPham] ([maSP])
GO
ALTER TABLE [dbo].[CTHoaDon] CHECK CONSTRAINT [FK_CTHoaDon_SanPham]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_Ban] FOREIGN KEY([maBan])
REFERENCES [dbo].[Ban] ([maBan])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_Ban]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_KhachHang] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVien]
GO
/****** Object:  Trigger [dbo].[themTaiKhoan]    Script Date: 11/9/2022 6:54:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 create trigger [dbo].[themTaiKhoan] on [dbo].[NhanVien]
 for insert
 as
 begin
	declare @manv nvarchar(50)
	declare @email varchar(50)

	select @manv =i.maNV, @email = i.email
	from inserted i

	insert TaiKhoan values (@email , '12345',@manv)
 end
GO
ALTER TABLE [dbo].[NhanVien] ENABLE TRIGGER [themTaiKhoan]
GO
/****** Object:  Trigger [dbo].[xoaTaiKhoan]    Script Date: 11/9/2022 6:54:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 CREATE trigger [dbo].[xoaTaiKhoan] on [dbo].[NhanVien]
	instead of delete
 as
 begin
	declare @manv nvarchar(50)
	declare @email varchar(50)

	select @manv =d.maNV, @email = d.email
	from deleted d

	delete from TaiKhoan where maNV = @manv
	delete from NhanVien where maNV = @manv
 end
GO
ALTER TABLE [dbo].[NhanVien] ENABLE TRIGGER [xoaTaiKhoan]
GO
