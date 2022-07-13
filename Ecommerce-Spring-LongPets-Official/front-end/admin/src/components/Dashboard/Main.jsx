import styled from "styled-components";
import "../../css/main.css";
import AccessAlarmIcon from '@mui/icons-material/AccessAlarm';
import { AutoGraphOutlined, BarChartOutlined, InsertChart, LineAxisOutlined } from "@mui/icons-material";
import { useEffect, useState } from "react";
import axios from "axios";
import format_money from "../../utils";
import React from "react";
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';

import { Line } from 'react-chartjs-2';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);
// 

const Container = styled.div`
    margin-top: 1.4rem;
`

const H1 = styled.h1`
    font-weight: 800;
    font-size: 1.5rem;
`

const Date = styled.div`
    display: inline-block;
    background: var(--color-light);
    border-radius: var(--border-radius-1);
    margin-top: 1rem;
    padding: 0.5rem 1.6rem;
`

const InputDate = styled.input`
    background: transparent;
    color: var(--color-dark);
`

const Insights = styled.div`
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 1.6rem;
    & > div {
        background: var(--color-white);
        padding: var(--card-padding);
        border-radius: var(--card-border-radius);
        margin-top: 1rem;
        box-shadow: var(--box-shadow);
        transition: all 300 ease; 
        &:hover {
            box-shadow: none;  
        }
    }
`

const Sales = styled.div`
    // & svg circle {
    //     stroke-dashoffset: -30;
    //     stroke-dasharray: 200;
    // }
`

const Expenses = styled.div`
    // & svg circle {
    //     stroke-dashoffset: 20;
    //     stroke-dasharray: 80;
    // }
`

const Income = styled.div`
    // & svg circle {
    //     stroke-dashoffset: 35;
    //     stroke-dasharray: 110;
    // }
`

const Icon = styled.span`
    
`

const Middle = styled.div`
    display: flex;
    align-items: center;
    justify-content: space-between;
`

const Left = styled.div`

`

const H3 = styled.h3`
    margin: 1rem 0 0.6rem;
    font-size: 1rem;
`

const Progress = styled.div`
    position: relative;
    width: 92px;
    height: 92px;
    border-radius: 50%;
`

const Svg = styled.svg`
    width: 7rem;
    height: 7rem;
`

const Circle = styled.circle`
    fill: none;
    stroke: var(--color-primary);
    stroke-width: 14;
    stroke-linecap: round;
    transform: translate(5px, 5px);
`

const ProgressNumber = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
`

const ProgressNumberP = styled.p`

`

const Small = styled.small`
    display: block;
    margin-top: 1.3rem;
`

// Recent Orders
const RecentOrders = styled.div`
    margin-top: 2rem;
`

const H2 = styled.h2`
    margin-bottom: 0.8rem;
`

const Table = styled.table`
    background: var(--color-white);
    width: 100%;
    border-radius: var(--card-border-radius);
    padding: var(--card-padding);
    text-align: center;
    box-shadow: var(--box-shadow);
    transition: all 300ms ease;
    &:hover {
        box-shadow: none;
    }
`

const Thead = styled.thead`

`

const Tr = styled.tr`
    &:last-child td {
        border: none;
    }
`

const Th = styled.th`

`

const Tbody = styled.tbody`

`

const Td = styled.td`
    height: 2.8rem;
    border-bottom: 1px solid var(--color-light);
`

const A = styled.a`
    text-align: center;
    display: block;
    margin: 1rem auto;
    color: var(--color-primary);
`

const Main = () => {
    const [dayMonthYear, setDayMonthYear] = useState();
    const [year, setYear] = useState("");
    const [month, setMonth] = useState("");
    const [day, setDay] = useState("");

    const [totalMoney, setTotalMoney] = useState();
    const [totalDog, setTotalDog] = useState();
    const [viewTotalDog, setViewTotalDog] = useState();
    const [totalCat, setTotalCat] = useState();
    const [viewTotalCat, setViewTotalCat] = useState();
    const [totalAnother, setTotalAnother] = useState();
    const [viewTotalAnother, setViewTotalAnother] = useState();
    // Thống kê doanh thu theo tháng từng danh mục
    const [totalMonth, setTotalMonth] = useState([]);


    useEffect(() => {
        console.log("Ngày, tháng, năm: ", day, month, year, dayMonthYear);

        // const getThongKeTheoDanhMuc = async () => {
        //     const thongketheodanhmucres = await axios.post("http://localhost:3001/api/products/getThongKeTheoDanhMuc", {});
        //     setThongKeTheoDanhMuc(thongketheodanhmucres.data);
        //     thongKeTheoDanhMuc.map((danhmuc, key) => {
        //         setTotalMoney(prev => prev + danhmuc.tongtiengiaodich);
        //     })
        // }
        const getTotalDog = async () => {
            if(day != "" && month != "" & year != "") {
                const totalDogRes = await axios.get(`http://localhost:8080/order/get-dog-profit/${day}-${month}-${year}`);
            }
            const totalDogRes = await axios.get("http://localhost:8080/order/get-dog-profit-year");
            setTotalDog(totalDogRes.data.moneyTotal);
            setViewTotalDog(format_money((totalDogRes.data.moneyTotal).toString()))
        }
        const getTotalCat = async () => {
            if(day != "" && month != "" & year != "") {
                const totalCatRes = await axios.get(`http://localhost:8080/order/get-cat-profit/${day}-${month}-${year}`);
            }
            const totalCatRes = await axios.get("http://localhost:8080/order/get-cat-profit-year");
            setTotalCat(totalCatRes.data.moneyTotal);
            setViewTotalCat(format_money((totalCatRes.data.moneyTotal).toString()))
        }
        const gettotalAnother = async () => {
            if(day != "" && month != "" & year != "") {
                const totalAnotherRes = await axios.get(`http://localhost:8080/order/get-another-profit/${day}-${month}-${year}`);
            }
            const totalAnotherRes = await axios.get("http://localhost:8080/order/get-another-profit-year");
            setTotalAnother(totalAnotherRes.data.moneyTotal);
            setViewTotalAnother(format_money((totalAnotherRes.data.moneyTotal).toString()))
        }
        const gettotalMoney = async () => {
            if(day != "" && month != "" & year != "") {
                const totalMoneyRes = await axios.get(`http://localhost:8080/order/get-money-total/${day}-${month}-${year}`);
            }
            const totalMoneyRes = await axios.get("http://localhost:8080/order/get-money-total-year");
            setTotalMoney(totalMoneyRes.data.moneyTotal);
        }
        // getThongKeTheoDanhMuc();
        getTotalDog();
        getTotalCat();
        gettotalAnother();
        gettotalMoney();
        return () => {
            setViewTotalDog("");
            setViewTotalCat("");
            setViewTotalAnother("");
        }

    }, [dayMonthYear])
    console.log(totalMoney, totalDog);
    const handleChangeDay = (dayMonthYear) => {
        setDayMonthYear(dayMonthYear);
        setYear(dayMonthYear.substring(0, 4));
        setMonth(dayMonthYear.substring(5, 7));
        setDay(dayMonthYear.substring(8, 10));
    }
    useEffect(() => {
        const gettotalMonth = async () => {
            try {
                const totalMonthres = await axios.get("http://localhost:8080/order/get-money-total-12-month");
                console.log("totalMonthres: ", totalMonthres.data);
                setTotalMonth(totalMonthres.data);
            } catch (err) {
                console.log("Lỗi khi lấy doanh thu theo tháng");
            }
        }
        gettotalMonth();
    }, [])

    var dataset = [
        totalMonth.map((danhmuc, key) => {
            return (
                {
                    data: [danhmuc.month1, danhmuc.month2, danhmuc.month3, danhmuc.month4, danhmuc.month5, danhmuc.month6, danhmuc.month7, danhmuc.month8, danhmuc.month9, danhmuc.month10, danhmuc.month11, danhmuc.month12],
                    label: danhmuc.categoryName,
                    borderColor: "#"+((1<<24)*Math.random()|0).toString(16),
                    fill: false
                }
            );
        })
    ];

    // -----------------------------------TEST-----------------------------------
    // console.log("Ngày, tháng, năm: ", ngay, month, nam, dayMonthYear);
    console.log("DATASET: ", dataset);
    console.log("DATASET-run duoc: ", [
        {
            data: [86, 114, 106, 106, 107, 111, 133, 221, 783, 2478],
            label: "Africa",
            borderColor: "#3e95cd",
            fill: false
        },
        {
            data: [282, 350, 411, 502, 635, 809, 947, 1402, 3700, 5267],
            label: "Asia",
            borderColor: "#8e5ea2",
            fill: false
        },
        {
            data: [168, 170, 178, 190, 203, 276, 408, 547, 675, 734],
            label: "Europe",
            borderColor: "#3cba9f",
            fill: false
        },
        {
            data: [40, 20, 10, 16, 24, 38, 74, 167, 508, 784],
            label: "Latin America",
            borderColor: "#e8c3b9",
            fill: false
        },
        {
            data: [6, 3, 2, 2, 7, 26, 82, 172, 312, 433],
            label: "North America",
            borderColor: "#c45850",
            fill: false
        }
    ]);

    return (
        <Container>
            <H1>Dashboard</H1>

            <Date>
                <InputDate type="date" onChange={(e) => handleChangeDay(e.target.value)} />
            </Date>

            <Insights>
                <Sales>
                    <Icon>
                        <InsertChart style={{ background: "var(--color-primary)", padding: "0.5rem", borderRadius: "50%", color: "var(--color-white)", fontSize: "3rem" }} />
                    </Icon>
                    <Middle>
                        <Left>
                            <H3>Total Chó</H3>
                            <H1>{viewTotalDog}<span style={{ textDecoration: "underline" }}><b>đ</b></span></H1>
                        </Left>
                        <Progress>
                            <Svg>
                                <Circle cx="38" cy="38" r="36" strokeDasharray="315" strokeDashoffset={Math.round(315 - (totalDog * 100 / totalMoney) * 2 * (360 / 315))}></Circle>
                            </Svg>
                            <ProgressNumber>
                                <ProgressNumberP>{Math.round(totalDog * 100 / totalMoney)}%</ProgressNumberP>
                            </ProgressNumber>
                        </Progress>
                    </Middle>
                    <Small className="text-muted">Last 24 Hours</Small>
                </Sales>
                {/* END OF SALES */}
                <Expenses>
                    <Icon>
                        <BarChartOutlined style={{ background: "var(--color-danger)", padding: "0.5rem", borderRadius: "50%", color: "var(--color-white)", fontSize: "3rem" }} />
                    </Icon>
                    <Middle>
                        <Left>
                            <H3>Total Mèo</H3>
                            <H1>{viewTotalCat}<span style={{ textDecoration: "underline" }}><b>đ</b></span></H1>
                        </Left>
                        <Progress>
                            <Svg>
                                <Circle cx="38" cy="38" r="36" strokeDasharray="315" strokeDashoffset={Math.round(315 - (totalCat * 100 / totalMoney) * 2 * (360 / 315))}></Circle>
                            </Svg>
                            <ProgressNumber>
                                <ProgressNumberP>{Math.round(totalCat * 100 / totalMoney)}%</ProgressNumberP>
                            </ProgressNumber>
                        </Progress>
                    </Middle>
                    <Small className="text-muted">Last 24 Hours</Small>
                </Expenses>
                {/* END OF EXPENSE */}
                <Income>
                    <Icon>
                        <AutoGraphOutlined style={{ background: "var(--color-success)", padding: "0.5rem", borderRadius: "50%", color: "var(--color-white)", fontSize: "3rem" }} />
                    </Icon>
                    <Middle>
                        <Left>
                            <H3>Total Thú cưng khác</H3>
                            <H1>{viewTotalAnother}<span style={{ textDecoration: "underline" }}><b>đ</b></span></H1>
                        </Left>
                        <Progress>
                            <Svg>
                                <Circle cx="38" cy="38" r="36" strokeDasharray="315" strokeDashoffset={Math.round(315 - (totalAnother * 100 / totalMoney) * 2 * (360 / 315))}></Circle>
                            </Svg>
                            <ProgressNumber>
                                <ProgressNumberP>{Math.round(totalAnother * 100 / totalMoney)}%</ProgressNumberP>
                            </ProgressNumber>
                        </Progress>
                    </Middle>
                    <Small className="text-muted">Last 24 Hours</Small>
                </Income>
                {/* END OF INCOME */}
            </Insights>
            {/* END OF INSIGHTS */}
            <RecentOrders>
                <H2>Thống kê Doanh thu từng danh mục thú cưng theo tháng (2022)</H2>
                <Line
                    data={{
                        labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
                        datasets: dataset[0]
                    }}
                    options={{
                        title: {
                            display: true,
                            text: "World population per region (in millions)"
                        },
                        legend: {
                            display: true,
                            position: "bottom"
                        }
                    }}
                />
                <A href="#">Show all</A>
            </RecentOrders>
        </Container>
    );
}

export default Main;